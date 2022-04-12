package com.amora.myseasonalanime.data.source.remote.response

import com.squareup.moshi.Json

data class Copy(

	@Json(name="pagination")
	val pagination: Pagination? = null,

	@Json(name="data")
	val data: List<DataItem?>? = null
)

data class Webp(

	@Json(name="large_image_url")
	val largeImageUrl: String? = null,

	@Json(name="small_image_url")
	val smallImageUrl: String? = null,

	@Json(name="image_url")
	val imageUrl: String? = null
)

data class From(

	@Json(name="month")
	val month: Int? = null,

	@Json(name="year")
	val year: Int? = null,

	@Json(name="day")
	val day: Int? = null
)

data class ProducersItem(

	@Json(name="name")
	val name: String? = null,

	@Json(name="mal_id")
	val malId: Int? = null,

	@Json(name="type")
	val type: String? = null,

	@Json(name="url")
	val url: String? = null
)

data class StudiosItem(

	@Json(name="name")
	val name: String? = null,

	@Json(name="mal_id")
	val malId: Int? = null,

	@Json(name="type")
	val type: String? = null,

	@Json(name="url")
	val url: String? = null
)

data class Broadcast(

	@Json(name="string")
	val string: String? = null,

	@Json(name="timezone")
	val timezone: String? = null,

	@Json(name="time")
	val time: String? = null,

	@Json(name="day")
	val day: String? = null
)

data class DemographicsItem(

	@Json(name="name")
	val name: String? = null,

	@Json(name="mal_id")
	val malId: Int? = null,

	@Json(name="type")
	val type: String? = null,

	@Json(name="url")
	val url: String? = null
)

data class To(

	@Json(name="month")
	val month: Any? = null,

	@Json(name="year")
	val year: Any? = null,

	@Json(name="day")
	val day: Any? = null
)

data class DataItem(

	@Json(name="title_japanese")
	val titleJapanese: String? = null,

	@Json(name="favorites")
	val favorites: Int? = null,

	@Json(name="broadcast")
	val broadcast: Broadcast? = null,

	@Json(name="year")
	val year: Int? = null,

	@Json(name="rating")
	val rating: String? = null,

	@Json(name="scored_by")
	val scoredBy: Int? = null,

	@Json(name="title_synonyms")
	val titleSynonyms: List<String?>? = null,

	@Json(name="source")
	val source: String? = null,

	@Json(name="title")
	val title: String? = null,

	@Json(name="type")
	val type: String? = null,

	@Json(name="trailer")
	val trailer: Trailer? = null,

	@Json(name="duration")
	val duration: String? = null,

	@Json(name="score")
	val score: Double? = null,

	@Json(name="themes")
	val themes: List<ThemesItem?>? = null,

	@Json(name="genres")
	val genres: List<GenresItem?>? = null,

	@Json(name="popularity")
	val popularity: Int? = null,

	@Json(name="members")
	val members: Int? = null,

	@Json(name="title_english")
	val titleEnglish: String? = null,

	@Json(name="rank")
	val rank: Int? = null,

	@Json(name="season")
	val season: String? = null,

	@Json(name="airing")
	val airing: Boolean? = null,

	@Json(name="episodes")
	val episodes: Int? = null,

	@Json(name="aired")
	val aired: Aired? = null,

	@Json(name="images")
	val images: Images? = null,

	@Json(name="studios")
	val studios: List<StudiosItem?>? = null,

	@Json(name="mal_id")
	val malId: Int? = null,

	@Json(name="synopsis")
	val synopsis: String? = null,

	@Json(name="explicit_genres")
	val explicitGenres: List<Any?>? = null,

	@Json(name="licensors")
	val licensors: List<Any?>? = null,

	@Json(name="url")
	val url: String? = null,

	@Json(name="producers")
	val producers: List<ProducersItem?>? = null,

	@Json(name="background")
	val background: Any? = null,

	@Json(name="status")
	val status: String? = null,

	@Json(name="demographics")
	val demographics: List<Any?>? = null
)

data class Trailer(

	@Json(name="images")
	val images: Images? = null,

	@Json(name="embed_url")
	val embedUrl: String? = null,

	@Json(name="youtube_id")
	val youtubeId: String? = null,

	@Json(name="url")
	val url: String? = null
)

data class Aired(

	@Json(name="string")
	val string: String? = null,

	@Json(name="prop")
	val prop: Prop? = null,

	@Json(name="from")
	val from: String? = null,

	@Json(name="to")
	val to: Any? = null
)

data class Prop(

	@Json(name="from")
	val from: From? = null,

	@Json(name="to")
	val to: To? = null
)

data class Images(

	@Json(name="jpg")
	val jpg: Jpg? = null,

	@Json(name="webp")
	val webp: Webp? = null,

	@Json(name="large_image_url")
	val largeImageUrl: String? = null,

	@Json(name="small_image_url")
	val smallImageUrl: String? = null,

	@Json(name="image_url")
	val imageUrl: String? = null,

	@Json(name="medium_image_url")
	val mediumImageUrl: String? = null,

	@Json(name="maximum_image_url")
	val maximumImageUrl: String? = null
)

data class Pagination(

	@Json(name="has_next_page")
	val hasNextPage: Boolean? = null,

	@Json(name="last_visible_page")
	val lastVisiblePage: Int? = null
)

data class ThemesItem(

	@Json(name="name")
	val name: String? = null,

	@Json(name="mal_id")
	val malId: Int? = null,

	@Json(name="type")
	val type: String? = null,

	@Json(name="url")
	val url: String? = null
)

data class Jpg(

	@Json(name="large_image_url")
	val largeImageUrl: String? = null,

	@Json(name="small_image_url")
	val smallImageUrl: String? = null,

	@Json(name="image_url")
	val imageUrl: String? = null
)

data class LicensorsItem(

	@Json(name="name")
	val name: String? = null,

	@Json(name="mal_id")
	val malId: Int? = null,

	@Json(name="type")
	val type: String? = null,

	@Json(name="url")
	val url: String? = null
)

data class GenresItem(

	@Json(name="name")
	val name: String? = null,

	@Json(name="mal_id")
	val malId: Int? = null,

	@Json(name="type")
	val type: String? = null,

	@Json(name="url")
	val url: String? = null
)
