package com.amora.myseasonalanime.data.source.remote.response.characters

import com.squareup.moshi.Json


data class CharaItem(

    @Json(name = "character")
    val character: Character? = null,

    @Json(name = "role")
    val role: String? = null,

    @Json(name = "voice_actors")
    val voiceActors: List<VoiceActorsItem?>? = null,
)