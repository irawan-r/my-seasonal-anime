package com.amora.myseasonalanime.data.source.remote.response.anime

import com.squareup.moshi.Json

data class AnimeListResponse(

    @Json(name = "pagination")
    val pagination: Pagination? = null,

    @Json(name = "data")
    val data: List<Anime>,
)

