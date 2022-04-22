package com.amora.myseasonalanime.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.amora.myseasonalanime.data.source.remote.api.ApiServices
import com.amora.myseasonalanime.data.source.remote.response.animenow.AnimeListResponse
import retrofit2.HttpException
import java.io.IOException

const val STARTING_PAGE_INDEX = 1

class AnimePagingSource(
    private val service: ApiServices,
) : PagingSource<Int, AnimeListResponse>() {
    override fun getRefreshKey(state: PagingState<Int, AnimeListResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnimeListResponse> {
        val pageIndex = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = service.getAiringAnime(pageIndex)
            val repos = response.data
            val nextKey = if (repos.isEmpty()) {
                null
            } else {
                pageIndex + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = repos,
                prevKey = if (pageIndex == STARTING_PAGE_INDEX) null else pageIndex,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}