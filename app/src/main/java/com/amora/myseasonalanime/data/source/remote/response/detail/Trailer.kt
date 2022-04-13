package com.amora.myseasonalanime.data.source.remote.response.detail

import com.amora.myseasonalanime.data.source.remote.response.images.Images
import com.squareup.moshi.Json

data class Trailer(

    @Json(name="images")
    val images: Images? = null,

    @Json(name="embed_url")
    val embedUrl: String? = null,

    @Json(name="youtube_id")
    val youtubeId: String? = null,

    @Json(name="url")
    val url: String? = null
)