package com.amora.myseasonalanime.data.source.remote.response.animenow

import com.amora.myseasonalanime.data.source.remote.response.animenow.images.ImagesNow
import com.squareup.moshi.Json

data class AnimeListResponse(

    @Json(name = "title")
    val title: String? = null,

    @Json(name = "images")
    val images: ImagesNow? = null,

    @Json(name = "mal_id")
    val malId: Int? = null,

    @Json(name = "score")
    val score: Double? = 0.0,
)