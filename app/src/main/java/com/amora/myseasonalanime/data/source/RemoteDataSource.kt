package com.amora.myseasonalanime.data.source

import com.amora.myseasonalanime.data.source.remote.api.ApiConfig
import com.amora.myseasonalanime.data.source.remote.response.detail.DataItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *  The first Data Source (Remote) that handle the processing retrofit so that will be used in Repository
 */
class RemoteDataSource private constructor(private val apiConfig: ApiConfig) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null
        fun getInstance(api: ApiConfig): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(api)
            }
    }

    suspend fun getSeasonNow(callback: GetAnimeCallback) {
        withContext(Dispatchers.IO) {
            val anime = apiConfig.apiServices.getSeasonNow().data
            callback.onAnimeReceived(anime)
        }
    }

    // Callback to get List<DataItem>
    interface GetAnimeCallback {
        fun onAnimeReceived(animeList: List<DataItem>)
    }
}