package com.amora.myseasonalanime.data

import com.amora.myseasonalanime.data.source.remote.response.animenow.AnimeListResponse
import com.amora.myseasonalanime.data.source.remote.response.characters.CharaItem
import com.amora.myseasonalanime.data.source.remote.response.detail.DetailAnimeResponse
import com.amora.myseasonalanime.data.source.remote.response.detail.Trailer

/**
 *  The function is simply to get object from data object (JSON)
 */
interface DataSource {
    suspend fun getSeasonsNow(): List<AnimeListResponse>

    suspend fun getAnimeId(id: Int): DetailAnimeResponse

    suspend fun getAnimeChara(id: Int): List<CharaItem>

    suspend fun getTrailer(id: Int): Trailer
}