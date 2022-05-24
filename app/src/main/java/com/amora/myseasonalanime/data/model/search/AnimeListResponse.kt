package com.amora.myseasonalanime.data.model.search

import com.amora.myseasonalanime.data.model.pagination.Pagination
import com.squareup.moshi.Json

data class AnimeSearchResponse(

    @Json(name = "pagination")
    val pagination: Pagination? = null,

    @Json(name = "data")
    val data: List<AnimeSearch>
)

