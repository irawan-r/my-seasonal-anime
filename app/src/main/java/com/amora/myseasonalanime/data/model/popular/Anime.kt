package com.amora.myseasonalanime.data.model.popular

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.amora.myseasonalanime.data.db.Converter
import com.amora.myseasonalanime.data.source.remote.response.images.Images
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity
data class Anime(

    @PrimaryKey
    @Json(name = "mal_id")
    val malId: Int? = null,

    @Json(name = "title")
    val title: String? = null,

    @TypeConverters(Converter::class)
    @Json(name = "images")
    val images: Images,

    @Json(name = "score")
    val score: Double? = 0.0
)