package com.amora.myseasonalanime.data.source.remote.response.voiceactor

import com.amora.myseasonalanime.data.source.remote.response.images.Images
import com.squareup.moshi.Json

data class DataItem(

    @Json(name="person")
    val person: Person? = null,

    @Json(name="language")
    val language: String? = null
)