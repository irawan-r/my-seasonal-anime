package com.amora.myseasonalanime.data.source.remote.response.characters.images

import com.squareup.moshi.Json

data class WebpChar(

    @Json(name = "image_url")
    val imageUrl: String? = null,
)
