package com.amora.myseasonalanime.data.source.remote.response.animenow.images

import com.squareup.moshi.Json

data class ImagesNow(

    @Json(name = "webp")
    val webp: WebpNow? = null,
)