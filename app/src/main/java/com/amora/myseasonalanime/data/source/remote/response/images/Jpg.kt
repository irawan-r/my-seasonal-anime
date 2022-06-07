package com.amora.myseasonalanime.data.source.remote.response.images

import com.squareup.moshi.Json

data class Jpg(

    @Json(name="image_url")
    val imageUrl: String? = null
)