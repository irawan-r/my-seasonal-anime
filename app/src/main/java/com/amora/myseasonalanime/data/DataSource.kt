package com.amora.myseasonalanime.data

import androidx.paging.PagingData
import com.amora.myseasonalanime.data.source.remote.response.anime.AnimeListResponse
import com.amora.myseasonalanime.data.source.remote.response.characters.CharaItem
import com.amora.myseasonalanime.data.source.remote.response.detailanime.DetailAnimeResponse
import com.amora.myseasonalanime.data.source.remote.response.detailcharacter.DetailAnimeCharaResponse
import com.amora.myseasonalanime.data.source.remote.response.trailer.TrailerItem
import com.amora.myseasonalanime.data.source.remote.response.voiceactor.DataItem
import kotlinx.coroutines.flow.Flow

/**
 *  The function is simply to receive object and pass it into data class (model)
 */
interface DataSource {
    suspend fun getAnimeAiring(page: Int): List<AnimeListResponse?>?

    suspend fun getUpcomingSeason(page: Int): List<AnimeListResponse?>?

    fun getMoreAiring(page: Int): Flow<PagingData<AnimeListResponse>>

    fun getMoreUpcoming(page: Int): Flow<PagingData<AnimeListResponse>>

    suspend fun getAnimeId(id: Int): DetailAnimeResponse

    suspend fun getAnimeChara(id: Int): List<CharaItem?>?

    suspend fun getDetailChara(id: Int): DetailAnimeCharaResponse?

    suspend fun getVoiceActor(id: Int): List<DataItem?>?

    suspend fun getAnimeTrailer(id: Int): List<TrailerItem?>?
}