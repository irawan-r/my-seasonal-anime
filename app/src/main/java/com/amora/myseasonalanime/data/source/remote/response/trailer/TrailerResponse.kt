package com.amora.myseasonalanime.data.source.remote.response.trailer

import com.squareup.moshi.Json

data class TrailerResponse(
	@Json(name = "data")
	val data: Data? = null,
)


