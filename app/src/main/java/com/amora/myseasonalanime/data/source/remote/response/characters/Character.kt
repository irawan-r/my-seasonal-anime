package com.amora.myseasonalanime.data.source.remote.response.characters

import com.amora.myseasonalanime.data.source.remote.response.characters.imageschar.ImagesChar
import com.squareup.moshi.Json

data class Character(

    @Json(name = "images")
    val images: ImagesChar? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "mal_id")
    val malId: Int? = null,

    @Json(name = "url")
    val url: String? = null,
)