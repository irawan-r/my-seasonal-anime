package com.amora.myseasonalanime.data.source.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.amora.myseasonalanime.data.db.RemoteKeys
import com.amora.myseasonalanime.data.db.RepoDatabase
import com.amora.myseasonalanime.data.source.remote.api.ApiServices
import com.amora.myseasonalanime.data.source.remote.response.anime.Anime
import retrofit2.HttpException
import java.io.IOException

private const val GITHUB_STARTING_PAGE_INDEX = 1

@OptIn(ExperimentalPagingApi::class)
class AnimeRemoteMediator(
    private val repoDatabase: RepoDatabase,
    private val services: ApiServices,
    private val type: String,
) : RemoteMediator<Int, Anime>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Anime>): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeysClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: GITHUB_STARTING_PAGE_INDEX
            }

            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                prevKey
            }

            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextKey
            }
        }

        try {
            val apiResponse = services.getTopAnime(type, page)
            val repos = apiResponse.data
            val endOfPaginationReached = repos.isEmpty()

            repoDatabase.withTransaction {
                // clear all tables in the database
                if (loadType == LoadType.REFRESH) {
                    repoDatabase.remoteKeysDao().clearRemoteKeys()
                    repoDatabase.animeDao().clearPopularAnime()
                }

                val prevKey = if (page == GITHUB_STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = repos.map {
                    it.malId?.let { malId ->
                        RemoteKeys(repoId = malId,
                            prevKey = prevKey,
                            nextKey = nextKey)
                    }
                }
                repoDatabase.remoteKeysDao().insertAll(keys)
                repoDatabase.animeDao().upsertPopularAnime(repos)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Anime>): RemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { repo ->
                // Get the remote keys of the last item retrieved
                repo.malId?.let { repoDatabase.remoteKeysDao().remoteKeysRepoId(it) }
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Anime>): RemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { repo ->
                repo.malId?.let { repoDatabase.remoteKeysDao().remoteKeysRepoId(it) }
            }
    }

    private suspend fun getRemoteKeysClosestToCurrentPosition(state: PagingState<Int, Anime>): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.malId?.let { repoId ->
                repoDatabase.remoteKeysDao().remoteKeysRepoId(repoId)
            }
        }
    }
}