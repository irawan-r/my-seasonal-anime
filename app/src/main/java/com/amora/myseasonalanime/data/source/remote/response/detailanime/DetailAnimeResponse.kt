package com.amora.myseasonalanime.data.source.remote.response.detailanime

import com.squareup.moshi.Json

data class DetailAnimeResponse(

    @Json(name="data")
    val data: DetailAnime
)