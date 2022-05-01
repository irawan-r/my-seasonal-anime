package com.amora.myseasonalanime.data.source.remote.response.anime.airing

import com.amora.myseasonalanime.data.source.remote.response.anime.AnimeListResponse
import com.amora.myseasonalanime.data.source.remote.response.anime.Pagination
import com.squareup.moshi.Json

data class AiringResponse(

    @Json(name = "pagination")
    val pagination: Pagination? = null,

    @Json(name = "data")
    val data: List<AnimeListResponse>,
)

