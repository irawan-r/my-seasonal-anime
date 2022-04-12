package com.amora.myseasonalanime.data.source.remote.response.detail

import com.squareup.moshi.Json

data class AnimeResponse(

	@Json(name = "pagination")
	val pagination: Pagination? = null,

	@Json(name = "data")
	val data: List<DetailItem>,
)

