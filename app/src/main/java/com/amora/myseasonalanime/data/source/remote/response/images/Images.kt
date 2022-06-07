package com.amora.myseasonalanime.data.source.remote.response.images

import androidx.room.TypeConverters
import com.amora.myseasonalanime.data.db.Converter
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import retrofit2.http.FormUrlEncoded

//@JsonClass(generateAdapter = true)
data class Images(

    @TypeConverters(Converter::class)
    @Json(name = "webp")
    val webp: Webp? = null,

    @TypeConverters(Converter::class)
    @Json(name = "jpg")
    val jpg: Jpg? = null,

    @Json(name = "large_image_url")
    val largeImageUrl: String? = null,

    @Json(name = "image_url")
    val imageUrl: String? = null,
)