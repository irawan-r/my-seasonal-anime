package com.amora.myseasonalanime.data

import androidx.paging.PagingData
import com.amora.myseasonalanime.data.source.RemoteDataSource
import com.amora.myseasonalanime.data.source.remote.response.anime.AnimeListResponse
import com.amora.myseasonalanime.data.source.remote.response.characters.CharaItem
import com.amora.myseasonalanime.data.source.remote.response.detailanime.DetailAnimeResponse
import com.amora.myseasonalanime.data.source.remote.response.detailcharacter.DetailAnimeCharaResponse
import com.amora.myseasonalanime.data.source.remote.response.trailer.TrailerItem
import com.amora.myseasonalanime.data.source.remote.response.voiceactor.DataItem
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

    override suspend fun getAnimeAiring(type: String, page: Int): List<AnimeListResponse?>? {
        var animeAiring: List<AnimeListResponse?>? = null
        remoteDataSource.getAnimeAiring(page, type, object : RemoteDataSource.GetAiringCallback {
            override fun onAnimeReceived(animeList: List<AnimeListResponse?>?) {
                animeAiring = animeList
            }
        })
        return animeAiring
    }

    override suspend fun getUpcomingSeason(type: String, page: Int): List<AnimeListResponse?>? {
        var animeUpcoming: List<AnimeListResponse?>? = null
        remoteDataSource.getUpComingSeason(page, type, object: RemoteDataSource.GetUpComingCallback {
            override fun onAnimeReceived(animeList: List<AnimeListResponse?>?) {
                animeUpcoming = animeList
            }
        })
        return animeUpcoming
    }

    override fun getMoreAiring(type: String, page: Int): Flow<PagingData<AnimeListResponse>> {
        return remoteDataSource.getMoreAiring()
    }

    override fun getMoreUpcoming(type: String, page: Int): Flow<PagingData<AnimeListResponse>> {
        return remoteDataSource.getMoreUpComing()
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

    override suspend fun getDetailChara(id: Int): DetailAnimeCharaResponse? {
        var detailCharaItem: DetailAnimeCharaResponse? = null
        remoteDataSource.getDetailChara(id, object : RemoteDataSource.GetAnimeDetailCharaCallback {
            override fun onAnimeReceived(animeDetailChara: DetailAnimeCharaResponse?) {
                detailCharaItem = animeDetailChara
            }
        })
        return detailCharaItem
    }

    override suspend fun getVoiceActor(id: Int): List<DataItem?>? {
        var voiceActorItem: List<DataItem?>? = null
        remoteDataSource.getVoiceActor(id, object : RemoteDataSource.GetVoiceActCallback {
            override fun onAnimeReceived(voiceAct: List<DataItem?>?) {
                voiceActorItem = voiceAct
            }
        })
        return voiceActorItem
    }

    override suspend fun getAnimeTrailer(id: Int): List<TrailerItem?>? {
        var animeTrailerItem: List<TrailerItem?>? = null
        remoteDataSource.getTrailerAnime(id, object : RemoteDataSource.GetTrailerAnimeCallback {
            override fun onAnimeReceived(animeTrailer: List<TrailerItem?>?) {
                animeTrailerItem = animeTrailer
            }
        })
        return animeTrailerItem
    }
}