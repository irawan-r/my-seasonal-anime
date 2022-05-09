package com.amora.myseasonalanime.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.amora.myseasonalanime.data.source.remote.api.ApiServices
import com.amora.myseasonalanime.data.source.remote.response.anime.Anime
import com.amora.myseasonalanime.utils.enum.Misc
import retrofit2.HttpException
import java.io.IOException

class TopAnimePagingSource(
    private val service: ApiServices,
    private val type: String,
    private val page: Int,
) : PagingSource<Int, Anime>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Anime> {
        val pageIndex = params.key ?: page
        val startingIndex = Misc.STARTING_PAGE_INDEX.item
        return try {
            val response = service.getTopAnime(type, pageIndex)
            val repos = response.data
            val nextKey = if (repos.isEmpty()) null else pageIndex + 1
            LoadResult.Page(
                data = repos,
                prevKey = if (pageIndex == startingIndex) null else pageIndex - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Anime>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
