package com.amora.myseasonalanime.data.source.remote.response.detailanime

import com.squareup.moshi.Json

data class Broadcast(

    @Json(name = "string")
    val string: String? = null,

    @Json(name = "timezone")
    val timezone: String? = null,

    @Json(name = "time")
    val time: String? = null,

    @Json(name = "day")
    val day: String? = null,
)