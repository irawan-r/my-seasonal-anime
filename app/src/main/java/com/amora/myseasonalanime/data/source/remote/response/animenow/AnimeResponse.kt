package com.amora.myseasonalanime.data.source.remote.response.animenow

import com.squareup.moshi.Json

data class AnimeResponse(

	@Json(name = "pagination")
    val pagination: Pagination? = null,

	@Json(name = "data")
    val data: List<AnimeListResponse>,
)

