package com.amora.myseasonalanime.data

import androidx.paging.PagingData
import com.amora.myseasonalanime.data.source.remote.response.animenow.AnimeListResponse
import com.amora.myseasonalanime.data.source.remote.response.characters.CharaItem
import com.amora.myseasonalanime.data.source.remote.response.detail.DetailAnimeResponse
import kotlinx.coroutines.flow.Flow

/**
 *  The function is simply to get object from data object (JSON)
 */
interface DataSource {
    suspend fun getAnimeAiring(page: Int): List<AnimeListResponse?>?

    fun getMoreAnime(page: Int): Flow<PagingData<AnimeListResponse>>

    suspend fun getAnimeId(id: Int): DetailAnimeResponse

    suspend fun getAnimeChara(id: Int): List<CharaItem?>?
}