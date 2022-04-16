package com.amora.myseasonalanime.data.source.remote.response.characters

import com.squareup.moshi.Json

data class CharactersResponse(

    @Json(name = "data")
    val data: List<CharaItem>,
)