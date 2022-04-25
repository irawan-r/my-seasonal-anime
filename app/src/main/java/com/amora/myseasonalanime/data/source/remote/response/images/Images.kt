package com.amora.myseasonalanime.data.source.remote.response.images

import com.squareup.moshi.Json

data class Images(

    @Json(name = "webp")
    val webp: Webp? = null,

    @Json(name = "large_image_url")
    val largeImageUrl: String? = null,

    @Json(name = "small_image_url")
    val smallImageUrl: String? = null,

    @Json(name = "image_url")
    val imageUrl: String? = null,

    @Json(name = "medium_image_url")
    val mediumImageUrl: String? = null,

    @Json(name = "maximum_image_url")
    val maximumImageUrl: String? = null,
)