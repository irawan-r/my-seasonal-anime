package com.amora.myseasonalanime.data

import com.amora.myseasonalanime.data.source.RemoteDataSource
import com.amora.myseasonalanime.data.source.remote.response.detail.DataItem

/** Retrieve the Api into Repository which had features caching and all viewModel get the source
 * data Api from here!
 */
class Repository private constructor(private val remoteDataSource: RemoteDataSource) : DataSource {

    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(remoteData: RemoteDataSource): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(remoteData)
            }
    }

    override suspend fun getSeasonNow(): List<DataItem> {
        lateinit var seasonNow: List<DataItem>
        remoteDataSource.getSeasonNow(object : RemoteDataSource.GetAnimeCallback {
            override fun onAnimeReceived(animeList: List<DataItem>) {
                seasonNow = animeList
            }
        })
        return seasonNow
    }
}