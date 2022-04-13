package com.amora.myseasonalanime.data.source.remote.response.characters

import com.squareup.moshi.Json

data class VoiceActorsItem(

    @Json(name="person")
    val person: Person? = null,

    @Json(name="language")
    val language: String? = null
)