package com.amora.myseasonalanime.data

import com.amora.myseasonalanime.data.source.remote.response.characters.CharaItem
import com.amora.myseasonalanime.data.source.remote.response.detail.DetailItem
import com.amora.myseasonalanime.data.source.remote.response.detail.Trailer

/**
 *  The function is simply to get object from data object (JSON)
 */
interface DataSource {
    suspend fun getSeasonsNow(): List<DetailItem>

    suspend fun getAnimeId(id: Int): DetailItem

    suspend fun getAnimeChara(id: Int): List<CharaItem>

    suspend fun getTrailer(id: Int): Trailer
}