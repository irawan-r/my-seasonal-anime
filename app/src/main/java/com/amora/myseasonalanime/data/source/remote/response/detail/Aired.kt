package com.amora.myseasonalanime.data.source.remote.response.detail

import com.squareup.moshi.Json

data class Aired(

    @Json(name = "string")
    val string: String? = null,

    @Json(name = "from")
    val from: String? = null,

    @Json(name = "to")
    val to: Any? = null,
)