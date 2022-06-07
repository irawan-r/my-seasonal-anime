package com.amora.myseasonalanime.data.source.remote.response.trailer

import com.squareup.moshi.Json

data class Data(
    @Json(name = "promo")
    val promo: List<TrailerItem?>? = null,

    @Json(name = "episodes")
    val episodes: List<Any?>? = null,
)