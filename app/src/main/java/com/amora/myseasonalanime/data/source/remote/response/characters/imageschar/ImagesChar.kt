package com.amora.myseasonalanime.data.source.remote.response.characters.imageschar

import com.amora.myseasonalanime.data.source.remote.response.detail.images.WebpChar
import com.squareup.moshi.Json

data class ImagesChar(

    @Json(name = "webp")
    val webp: WebpChar? = null,
)