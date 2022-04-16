package com.amora.myseasonalanime.data.source.remote.response

import com.amora.myseasonalanime.data.source.remote.response.detail.DetailItem
import com.amora.myseasonalanime.data.source.remote.response.detail.Pagination
import com.squareup.moshi.Json

data class AnimeResponse(

	@Json(name = "pagination")
	val pagination: Pagination? = null,

	@Json(name = "data")
	val data: List<DetailItem>,
)

