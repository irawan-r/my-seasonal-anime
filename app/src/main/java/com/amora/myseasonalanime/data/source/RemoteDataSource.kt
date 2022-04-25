package com.amora.myseasonalanime.data.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.amora.myseasonalanime.data.source.remote.api.ApiConfig
import com.amora.myseasonalanime.data.source.remote.response.animenow.AnimeListResponse
import com.amora.myseasonalanime.data.source.remote.response.characters.CharaItem
import com.amora.myseasonalanime.data.source.remote.response.detail.DetailAnimeResponse
import com.amora.myseasonalanime.data.source.remote.response.trailer.TrailerItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

/**
 *  The first Data Source (Remote) that handle the processing retrofit so that will be used in Repository
 */

const val NETWORK_PAGE_SIZE = 25

class RemoteDataSource private constructor(private val apiConfig: ApiConfig) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null
        fun getInstance(api: ApiConfig): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(api)
            }
    }

    suspend fun getAnimeAiring(page: Int, callback: GetAnimeCallback) {
        withContext(Dispatchers.IO) {
            val anime = apiConfig.api.getAiringAnime(page).data
            callback.onAnimeReceived(anime)
        }
    }

    fun getMoreAnime(): Flow<PagingData<AnimeListResponse>> {
        val services = apiConfig.api
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { AnimePagingSource(services) }
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

    suspend fun getTrailerAnime(id: Int, callback: GetTrailerAnimeCallback) {
        withContext(Dispatchers.IO) {
            val animeTrailer = apiConfig.api.getAnimeTrailer(id).data?.promo
            callback.onAnimeReceived(animeTrailer)
        }
    }

    interface GetAnimeCallback {
        fun onAnimeReceived(animeList: List<AnimeListResponse?>?)
    }

    interface GetAnimeIdCallback {
        fun onAnimeReceived(animeId: DetailAnimeResponse)
    }

    interface GetAnimeCharaCallback {
        fun onAnimeReceived(animeChara: List<CharaItem?>?)
    }

    interface GetTrailerAnimeCallback {
        fun onAnimeReceived(animeTrailer: List<TrailerItem?>?)
    }
}