package com.amora.myseasonalanime.data

import com.amora.myseasonalanime.data.source.RemoteDataSource
import com.amora.myseasonalanime.data.source.remote.response.characters.CharaItem
import com.amora.myseasonalanime.data.source.remote.response.detail.DetailItem
import com.amora.myseasonalanime.data.source.remote.response.detail.Trailer

/** Get the RemoteDataSource and passing into DataSource
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

    override suspend fun getSeasonNow(): List<DetailItem> {
        lateinit var seasonNow: List<DetailItem>
        remoteDataSource.getSeasonNow(object : RemoteDataSource.GetAnimeCallback {
            override fun onAnimeReceived(animeList: List<DetailItem>) {
                seasonNow = animeList
            }
        })
        return seasonNow
    }

    override suspend fun getAnimeId(id: Int): DetailItem {
        lateinit var animeDetailId: DetailItem
        remoteDataSource.getAnimeId(id, object : RemoteDataSource.GetAnimeIdCallback {
            override fun onAnimeReceived(animeId: DetailItem) {
                animeDetailId = animeId
            }
        })
        return animeDetailId
    }


    override suspend fun getAnimeChara(id: Int): List<CharaItem> {
        lateinit var animeCharaItem: List<CharaItem>
        remoteDataSource.getAnimeChara(id, object : RemoteDataSource.GetAnimeCharaCallback {
            override fun onAnimeReceived(animeChara: List<CharaItem>) {
                animeCharaItem = animeChara
            }
        })
        return animeCharaItem
    }

    override suspend fun getTrailer(id: Int): Trailer {
        lateinit var animeTrailerList: Trailer
        remoteDataSource.getAnimeTrailer(id, object : RemoteDataSource.GetAnimeTrailerCallback {
            override fun onAnimeReceived(animeTrailer: Trailer) {
                animeTrailerList = animeTrailer
            }
        })
        return animeTrailerList
    }

}