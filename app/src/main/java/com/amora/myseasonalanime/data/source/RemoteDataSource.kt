package com.amora.myseasonalanime.data.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.amora.myseasonalanime.data.source.paging.AiringPagingSource
import com.amora.myseasonalanime.data.source.paging.UpcomingPagingSource
import com.amora.myseasonalanime.data.source.remote.api.ApiConfig
import com.amora.myseasonalanime.data.source.remote.response.characters.CharaItems
import com.amora.myseasonalanime.data.source.remote.response.detailanime.DetailAnimeResponse
import com.amora.myseasonalanime.data.source.remote.response.detailcharacter.DetailAnimeCharaResponse
import com.amora.myseasonalanime.data.source.remote.response.trailer.TrailerItem
import com.amora.myseasonalanime.data.source.remote.response.voiceactor.VoiceActors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

/**
 *  The first DetailCharaItem Source (Remote) that handle the processing retrofit so that will be used in Repository
 */

const val NETWORK_PAGE_SIZE = 25
const val STARTING_PAGE_INDEX = 1

class RemoteDataSource private constructor(private val apiConfig: ApiConfig) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null
        fun getInstance(api: ApiConfig): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(api)
            }
    }

    suspend fun getAnime(page: Int, type: String, callback: GetAnimeCallback) {
        withContext(Dispatchers.IO) {
            val anime = apiConfig.api.getAnime(type, page).data
            callback.onAnimeReceived(anime)
        }
    }

    fun getMoreAiring(): Flow<PagingData<com.amora.myseasonalanime.data.source.remote.response.anime.Anime>> {
        val services = apiConfig.api
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { AiringPagingSource(services) }
        ).flow
    }

    fun getMoreUpComing(): Flow<PagingData<com.amora.myseasonalanime.data.source.remote.response.anime.Anime>> {
        val services = apiConfig.api
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { UpcomingPagingSource(services) }
        ).flow
    }

    suspend fun getAnimeId(id: Int, callback: GetAnimeIdCallback) {
        withContext(Dispatchers.IO) {
            val animeId = apiConfig.api.getAnimeId(id)
            callback.onAnimeReceived(animeId)
        }
    }

    suspend fun getAnimeChara(id: Int, callback: GetAnimeCharaCallback) {
        withContext(Dispatchers.IO) {
            val animeChara = apiConfig.api.getAnimeCharacters(id).data
            callback.onAnimeReceived(animeChara)
        }
    }

    suspend fun getDetailChara(id: Int, callback: GetAnimeDetailCharaCallback) {
        withContext(Dispatchers.IO) {
            val detailChara = apiConfig.api.getDetailAnimeCharacters(id)
            callback.onAnimeReceived(detailChara)
        }
    }

    suspend fun getVoiceActor(id: Int, callback: GetVoiceActCallback) {
        withContext(Dispatchers.IO) {
            val voiceAct = apiConfig.api.getVoiceActor(id).data
            callback.onAnimeReceived(voiceAct)
        }
    }

    suspend fun getTrailerAnime(id: Int, callback: GetTrailerAnimeCallback) {
        withContext(Dispatchers.IO) {
            val animeTrailer = apiConfig.api.getAnimeTrailer(id).data?.promo
            callback.onAnimeReceived(animeTrailer)
        }
    }

    interface GetAnimeCallback {
        fun onAnimeReceived(animeList: List<com.amora.myseasonalanime.data.source.remote.response.anime.Anime?>?)
    }

    interface GetAnimeIdCallback {
        fun onAnimeReceived(animeId: DetailAnimeResponse)
    }

    interface GetAnimeCharaCallback {
        fun onAnimeReceived(animeChara: List<CharaItems?>?)
    }

    interface GetAnimeDetailCharaCallback {
        fun onAnimeReceived(animeDetailChara: DetailAnimeCharaResponse?)
    }

    interface GetVoiceActCallback {
        fun onAnimeReceived(voiceAct: List<VoiceActors?>?)
    }

    interface GetTrailerAnimeCallback {
        fun onAnimeReceived(animeTrailer: List<TrailerItem?>?)
    }
}