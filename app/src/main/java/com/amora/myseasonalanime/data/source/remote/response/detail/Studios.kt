package com.amora.myseasonalanime.data.source.remote.response.detail

import com.squareup.moshi.Json


data class Studios(

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "mal_id")
    val malId: Int? = null,

    @Json(name = "type")
    val type: String? = null,

    @Json(name = "url")
    val url: String? = null,
)