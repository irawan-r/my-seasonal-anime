package com.amora.myseasonalanime.data.source.remote.response.characters

import com.squareup.moshi.Json


data class CharaItems(

    @Json(name = "character")
    val character: Character? = null,

    @Json(name = "role")
    val role: String? = null,
)