package com.amora.myseasonalanime.data

import androidx.paging.PagingData
import com.amora.myseasonalanime.data.source.RemoteDataSource
import com.amora.myseasonalanime.data.source.remote.response.anime.Anime
import com.amora.myseasonalanime.data.source.remote.response.characters.CharaItems
import com.amora.myseasonalanime.data.source.remote.response.detailanime.DetailAnimeResponse
import com.amora.myseasonalanime.data.source.remote.response.detailcharacter.DetailAnimeCharaResponse
import com.amora.myseasonalanime.data.source.remote.response.trailer.TrailerItem
import com.amora.myseasonalanime.data.source.remote.response.voiceactor.VoiceActors
import kotlinx.coroutines.flow.Flow

/** Get the RemoteDataSource and passing into DataSource
 */
class Repository private constructor(
    private val remoteDataSource: RemoteDataSource,
) : DataSource {

    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(remoteData: RemoteDataSource): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(remoteData)
            }
    }

    override fun searchAnime(query: String): Flow<PagingData<Anime>> {
        return remoteDataSource.getSearchAnime(query)
    }

    override suspend fun getAnime(type: String, page: Int): List<Anime?>? {
        var animeAiring: List<Anime?>? = null
        remoteDataSource.getAnime(type, page, object : RemoteDataSource.GetAnimeCallback {
            override fun onAnimeReceived(animeList: List<Anime?>?) {
                animeAiring = animeList
            }
        })
        return animeAiring
    }

    override fun getTopAnime(filter: String): Flow<PagingData<Anime>> {
        return remoteDataSource.getTopAnime(filter)
    }

    override fun getMoreAnime(type: String, page: Int): Flow<PagingData<Anime>> {
        return remoteDataSource.getMoreAnime(type, page)
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

    override suspend fun getAnimeChara(id: Int): List<CharaItems?>? {
        var animeCharaItems: List<CharaItems?>? = null
        remoteDataSource.getAnimeChara(id, object : RemoteDataSource.GetAnimeCharaCallback {
            override fun onAnimeReceived(animeChara: List<CharaItems?>?) {
                animeCharaItems = animeChara
            }
        })
        return animeCharaItems
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

    override suspend fun getVoiceActor(id: Int): List<VoiceActors?>? {
        var voiceActorsItem: List<VoiceActors?>? = null
        remoteDataSource.getVoiceActor(id, object : RemoteDataSource.GetVoiceActCallback {
            override fun onAnimeReceived(voiceAct: List<VoiceActors?>?) {
                voiceActorsItem = voiceAct
            }
        })
        return voiceActorsItem
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