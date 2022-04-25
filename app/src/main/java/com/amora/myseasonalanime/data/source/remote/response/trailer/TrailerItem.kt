package com.amora.myseasonalanime.data.source.remote.response.trailer

import com.squareup.moshi.Json

data class TrailerItem(
    @Json(name = "trailer")
    val trailer: Trailer? = null,

    @Json(name = "title")
    val title: String? = null,
)
