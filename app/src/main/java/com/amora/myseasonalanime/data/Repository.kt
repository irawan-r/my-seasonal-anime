package com.amora.myseasonalanime.data

import com.amora.myseasonalanime.data.source.RemoteDataSource
import com.amora.myseasonalanime.data.source.remote.response.animenow.AnimeListResponse
import com.amora.myseasonalanime.data.source.remote.response.characters.CharaItem
import com.amora.myseasonalanime.data.source.remote.response.detail.DetailAnimeResponse

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

    override suspend fun getSeasonsNow(): List<AnimeListResponse?>? {
        var seasonNow: List<AnimeListResponse?>? = null
        remoteDataSource.getSeasonsNow(object : RemoteDataSource.GetAnimeCallback {
            override fun onAnimeReceived(animeList: List<AnimeListResponse?>?) {
                 seasonNow = animeList
            }
        })
        return seasonNow
    }

    override suspend fun getAnimeId(id: Int): DetailAnimeResponse {
        lateinit var animeDetailId: DetailAnimeResponse
        remoteDataSource.getAnimeId(id, object : RemoteDataSource.GetAnimeIdCallback {
            override fun onAnimeReceived(animeId: DetailAnimeResponse) {
                animeDetailId = animeId
            }
        })
        return animeDetailId
    }


    override suspend fun getAnimeChara(id: Int): List<CharaItem?>? {
        var animeCharaItem: List<CharaItem?>? = null
        remoteDataSource.getAnimeChara(id, object : RemoteDataSource.GetAnimeCharaCallback {
            override fun onAnimeReceived(animeChara: List<CharaItem?>?) {
                animeCharaItem = animeChara
            }
        })
        return animeCharaItem
    }

    /*override suspend fun getTrailer(id: Int): Trailer {
        lateinit var animeTrailerList: Trailer
        remoteDataSource.getAnimeTrailer(id, object : RemoteDataSource.GetAnimeTrailerCallback {
            override fun onAnimeReceived(animeTrailer: Trailer) {
                animeTrailerList = animeTrailer
            }
        })
        return animeTrailerList
    }*/

}