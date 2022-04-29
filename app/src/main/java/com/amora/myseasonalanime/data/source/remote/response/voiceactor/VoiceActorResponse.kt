package com.amora.myseasonalanime.data.source.remote.response.voiceactor

import com.amora.myseasonalanime.data.source.remote.response.images.Images
import com.squareup.moshi.Json

data class VoiceActorResponse(

	@Json(name="data")
	val data: List<DataItem?>? = null
)
