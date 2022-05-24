package com.amora.myseasonalanime.data

import androidx.paging.PagingData
import com.amora.myseasonalanime.data.model.popular.Anime
import com.amora.myseasonalanime.data.model.search.AnimeSearch
import com.amora.myseasonalanime.data.source.remote.response.characters.CharaItems
import com.amora.myseasonalanime.data.source.remote.response.detailanime.DetailAnimeResponse
import com.amora.myseasonalanime.data.source.remote.response.detailcharacter.DetailAnimeCharaResponse
import com.amora.myseasonalanime.data.source.remote.response.trailer.TrailerItem
import com.amora.myseasonalanime.data.source.remote.response.voiceactor.VoiceActors
import kotlinx.coroutines.flow.Flow

/**
 *  The function is simply to receive object and pass it into trailerItems class (model)
 */
interface DataSource {
    fun searchAnime(query: String): Flow<PagingData<AnimeSearch>>

    fun getPopularAnime(filter: String): Flow<PagingData<Anime>>

    suspend fun getAnime(type: String, page: Int): List<Anime?>?

    fun getMoreAnime(type: String, page: Int): Flow<PagingData<Anime>>

    suspend fun getAnimeId(id: Int): DetailAnimeResponse

    suspend fun getAnimeChara(id: Int): List<CharaItems?>?

    suspend fun getDetailChara(id: Int): DetailAnimeCharaResponse?

    suspend fun getVoiceActor(id: Int): List<VoiceActors?>?

    suspend fun getAnimeTrailer(id: Int): List<TrailerItem?>?
}