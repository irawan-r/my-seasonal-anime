package com.amora.myseasonalanime.data.source.remote.response.detailcharacter

import com.squareup.moshi.Json

data class DetailAnimeCharaResponse(

	@Json(name="data")
	val data: DetailCharaItem? = null
)

