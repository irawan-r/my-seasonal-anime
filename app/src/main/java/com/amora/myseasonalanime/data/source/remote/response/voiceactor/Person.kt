package com.amora.myseasonalanime.data.source.remote.response.voiceactor

import com.amora.myseasonalanime.data.source.remote.response.images.Images
import com.squareup.moshi.Json


data class Person(

    @Json(name="images")
    val images: Images? = null,

    @Json(name="name")
    val name: String? = null,

    @Json(name="mal_id")
    val malId: Int? = null,

    @Json(name="url")
    val url: String? = null
)