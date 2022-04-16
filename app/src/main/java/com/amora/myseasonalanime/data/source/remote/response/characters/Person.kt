package com.amora.myseasonalanime.data.source.remote.response.characters

import android.provider.MediaStore
import com.amora.myseasonalanime.data.source.remote.response.detail.images.ImagesChar
import com.squareup.moshi.Json

data class Person(

    @Json(name = "images")
    val images: ImagesChar,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "mal_id")
    val malId: Int? = null,

    @Json(name = "url")
    val url: String? = null,
)