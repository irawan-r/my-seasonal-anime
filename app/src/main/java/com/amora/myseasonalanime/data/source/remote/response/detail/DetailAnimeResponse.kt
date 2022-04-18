package com.amora.myseasonalanime.data.source.remote.response.detail

import com.squareup.moshi.Json

data class DetailAnimeResponse(

    @Json(name="data")
    val data: DetailAnime? = null
)