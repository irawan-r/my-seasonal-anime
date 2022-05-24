package com.amora.myseasonalanime.data.model.popular

import com.amora.myseasonalanime.data.model.pagination.Pagination
import com.squareup.moshi.Json

data class AnimeListResponse(

    @Json(name = "pagination")
    val pagination: Pagination? = null,

    @Json(name = "data")
    val data: List<Anime>,
)

