package com.amora.myseasonalanime.data.source.remote.response.detail

import com.amora.myseasonalanime.data.source.remote.response.detail.images.ImagesDetail
import com.squareup.moshi.Json

data class DetailAnime(

    @Json(name = "title_japanese")
    val titleJapanese: String? = null,

    @Json(name = "favorites")
    val favorites: Int? = null,

    @Json(name = "broadcast")
    val broadcast: Broadcast? = null,

    @Json(name = "year")
    val year: Int? = null,

    @Json(name = "rating")
    val rating: String? = null,

    @Json(name = "scored_by")
    val scoredBy: Int? = null,

    @Json(name = "title_synonyms")
    val titleSynonyms: List<String?>? = null,

    @Json(name = "source")
    val source: String? = null,

    @Json(name = "title")
    val title: String? = null,

    @Json(name = "type")
    val type: String? = null,

    @Json(name = "trailer")
    val trailer: Trailer?,

    @Json(name = "duration")
    val duration: String? = null,

    @Json(name = "score")
    val score: Double? = 0.0,

    @Json(name = "genres")
    val genres: List<GenresItem>,

    @Json(name = "popularity")
    val popularity: Int? = null,

    @Json(name = "members")
    val members: Int? = null,

    @Json(name = "title_english")
    val titleEnglish: String? = null,

    @Json(name = "rank")
    val rank: Int? = null,

    @Json(name = "season")
    val season: String? = null,

    @Json(name = "airing")
    val airing: Boolean? = null,

    @Json(name = "episodes")
    val episodes: Int? = null,

    @Json(name = "aired")
    val aired: Aired? = null,

    @Json(name = "images")
    val images: ImagesDetail?,

    @Json(name = "studios")
    val studios: List<Studios?>? = null,

    @Json(name = "mal_id")
    val malId: Int? = null,

    @Json(name = "synopsis")
    val synopsis: String? = null,

    @Json(name = "explicit_genres")
    val explicitGenres: List<Any?>? = null,

    @Json(name = "licensors")
    val licensors: List<Any?>? = null,

    @Json(name = "url")
    val url: String? = null,

    @Json(name = "producers")
    val producers: List<Producers?>? = null,

    @Json(name = "background")
    val background: Any? = null,

    @Json(name = "status")
    val status: String? = null,

    @Json(name = "demographics")
    val demographics: List<Any?>? = null,
)