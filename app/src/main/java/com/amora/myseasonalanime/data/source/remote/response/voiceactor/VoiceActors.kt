package com.amora.myseasonalanime.data.source.remote.response.voiceactor

import com.amora.myseasonalanime.data.source.remote.response.images.Images
import com.squareup.moshi.Json

data class VoiceActors(

    @Json(name="person")
    val person: Person? = null,

    @Json(name="language")
    val language: String? = null
)