package com.amora.myseasonalanime.data.source.remote.response

import com.amora.myseasonalanime.data.source.remote.response.detail.DataItem
import com.squareup.moshi.Json

data class AnimeResponse(
	@Json(name = "data")
	val data: List<DataItem>
)

