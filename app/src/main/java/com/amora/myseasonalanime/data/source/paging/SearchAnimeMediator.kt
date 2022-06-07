package com.amora.myseasonalanime.data.source.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.amora.myseasonalanime.data.db.RemoteKeys
import com.amora.myseasonalanime.data.db.RepoDatabase
import com.amora.myseasonalanime.data.model.search.AnimeSearch
import com.amora.myseasonalanime.data.source.remote.api.ApiServices
import retrofit2.HttpException
import java.io.IOException

private const val GITHUB_STARTING_PAGE_INDEX = 1

@OptIn(ExperimentalPagingApi::class)
class SearchAnimeMediator(
    private val query: String,
    private val repoDatabase: RepoDatabase,
    private val services: ApiServices,
) : RemoteMediator<Int, AnimeSearch>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, AnimeSearch>,
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeysClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: GITHUB_STARTING_PAGE_INDEX
            }

            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                // If remoteKeys is null, that means the refresh result is not in the database yet.
                val prevKey = remoteKeys?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                prevKey
            }

            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                // If remoteKeys is null, that means the refresh result is not in the database yet.
                // We can return Success with endOfPaginationReached = false because Paging
                // will call this method again if RemoteKeys becomes non-null.
                // If remoteKeys is NOT NULL but its nextKey is null, that means we've reached
                // the end of pagination for append.
                val nextKey = remoteKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextKey
            }
        }
        Log.d("page", "$page")
        try {
            val apiResponse = services.searchAnime(query, page)
            val repos = apiResponse.data
            val endOfPaginationReached = repos.isEmpty()

            repoDatabase.withTransaction {
                // clear all tables in the database
                if (loadType == LoadType.REFRESH) {
                    repoDatabase.remoteKeysDao().clearRemoteKeys()
                    repoDatabase.animeDao().clearSearchAnime()
                }
                val prevKey = if (page == GITHUB_STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1

                // Clear! not null both $next and $prev
                // next = 3, but the page = 2, interesting
                 Log.d("next", "$nextKey")
                 Log.d("prev", "$prevKey")

                val keys = repos.map {
                    it.malId?.let { id ->
                        RemoteKeys(
                            repoId = id,
                            prevKey = prevKey,
                            nextKey = nextKey
                        )
                    }
                }
                repoDatabase.remoteKeysDao().insertAll(keys)
                repoDatabase.animeDao().upsertSearchAnime(repos)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    /*fun getRefreshKey(state: PagingState<Int, AnimeSearch>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }*/

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, AnimeSearch>): RemoteKeys? {
        // Get the last page that was retrieved, that contained items.
        // From that last page, get last item
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { repo ->
                // Get the remote keys of the last item retrieved
                repo.malId.let {
                    it?.let { id ->
                        repoDatabase.remoteKeysDao().remoteKeysRepoId(id)
                    }
                }
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, AnimeSearch>): RemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { repo ->
                repo.malId.let {
                    it?.let { id ->
                        repoDatabase.remoteKeysDao().remoteKeysRepoId(id)
                    }
                }
            }
    }

    private suspend fun getRemoteKeysClosestToCurrentPosition(state: PagingState<Int, AnimeSearch>): RemoteKeys? {
        // The paging library is trying to load data after the anchor position
        // Get the item closest to the anchor position
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.malId?.let { repoId ->
                repoDatabase.remoteKeysDao().remoteKeysRepoId(repoId)
            }
        }
        /*return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }*/
    }
}