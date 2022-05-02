package com.amora.myseasonalanime.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.amora.myseasonalanime.data.source.STARTING_PAGE_INDEX
import com.amora.myseasonalanime.data.source.remote.api.ApiServices
import com.amora.myseasonalanime.data.source.remote.response.anime.AnimeListResponse
import com.amora.myseasonalanime.utils.enum.More
import retrofit2.HttpException
import java.io.IOException

class AiringPagingSource(
    private val service: ApiServices
) : PagingSource<Int, AnimeListResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnimeListResponse> {
        val pageIndex = params.key ?: STARTING_PAGE_INDEX
        return try {
            val airing = More.AIRING.type
            val response = service.getAiringAnime(airing, pageIndex)
            val repos = response.data
            val nextKey = if (repos.isEmpty()) null else pageIndex + 1
            LoadResult.Page(
                data = repos,
                prevKey = if (pageIndex == STARTING_PAGE_INDEX) null else pageIndex - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, AnimeListResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}