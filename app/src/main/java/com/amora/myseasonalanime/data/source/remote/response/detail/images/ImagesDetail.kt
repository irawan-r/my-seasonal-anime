package com.amora.myseasonalanime.data.source.remote.response.detail.images

import com.squareup.moshi.Json

data class ImagesDetail(

    @Json(name = "webp")
    val webp: WebpDetail? = null,
)