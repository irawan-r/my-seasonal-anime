package com.amora.myseasonalanime.data

import androidx.paging.PagingData
import com.amora.myseasonalanime.data.source.RemoteDataSource
import com.amora.myseasonalanime.data.source.remote.response.animenow.AnimeListResponse
import com.amora.myseasonalanime.data.source.remote.response.characters.CharaItem
import com.amora.myseasonalanime.data.source.remote.response.detail.DetailAnimeResponse
import kotlinx.coroutines.flow.Flow

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

    override suspend fun getAnimeAiring(page: Int): List<AnimeListResponse?>? {
        var animeAiring: List<AnimeListResponse?>? = null
        remoteDataSource.getAnimeAiring(page, object : RemoteDataSource.GetAnimeCallback {
            override fun onAnimeReceived(animeList: List<AnimeListResponse?>?) {
                 animeAiring = animeList
            }
        })
        return animeAiring
    }

    override fun getMoreAnime(page: Int): Flow<PagingData<AnimeListResponse>> {
        return remoteDataSource.getMoreAnime()
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
}