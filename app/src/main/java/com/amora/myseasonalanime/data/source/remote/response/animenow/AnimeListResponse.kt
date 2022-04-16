package com.amora.myseasonalanime.data.source.remote.response.animenow

import com.amora.myseasonalanime.data.source.remote.response.detail.images.ImagesChar
import com.squareup.moshi.Json

data class AnimeListResponse(

    @Json(name = "title")
    val title: String? = null,

    @Json(name = "images")
    val images: ImagesChar? = null,

    @Json(name = "mal_id")
    val malId: Int? = null,
)