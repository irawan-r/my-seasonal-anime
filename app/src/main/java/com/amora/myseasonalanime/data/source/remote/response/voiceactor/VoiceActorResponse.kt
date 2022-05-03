package com.amora.myseasonalanime.data.source.remote.response.voiceactor

import com.squareup.moshi.Json

data class VoiceActorResponse(

	@Json(name="data")
	val data: List<VoiceActors?>? = null
)
