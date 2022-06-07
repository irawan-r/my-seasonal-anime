package com.amora.myseasonalanime.data.source.remote.response.detailcharacter

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.amora.myseasonalanime.data.source.remote.response.images.Images
import com.squareup.moshi.Json

@Entity(tableName = "detail_chara")
data class DetailCharaItem(

    @Json(name="favorites")
    val favorites: Int? = null,

    @Json(name="images")
    val images: Images? = null,

    @Json(name="name_kanji")
    val nameKanji: String? = null,

    @Json(name="name")
    val name: String? = null,

    @Json(name="about")
    val about: String? = null,

    @PrimaryKey
    @Json(name="mal_id")
    val malId: Int? = null,

    @Json(name="nicknames")
    val nicknames: List<String?>? = null,

    @Json(name="url")
    val url: String? = null
)