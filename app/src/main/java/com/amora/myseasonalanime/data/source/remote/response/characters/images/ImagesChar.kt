package com.amora.myseasonalanime.data.source.remote.response.characters.images

import com.squareup.moshi.Json

data class ImagesChar(

    @Json(name = "webp")
    val webp: WebpChar? = null,
)