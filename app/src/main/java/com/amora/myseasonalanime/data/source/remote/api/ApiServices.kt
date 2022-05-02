package com.amora.myseasonalanime.data.source.remote.api

import com.amora.myseasonalanime.data.source.remote.response.anime.airing.AiringResponse
import com.amora.myseasonalanime.data.source.remote.response.anime.upcoming.UpcomingResponse
import com.amora.myseasonalanime.data.source.remote.response.characters.CharactersResponse
import com.amora.myseasonalanime.data.source.remote.response.detailanime.DetailAnimeResponse
import com.amora.myseasonalanime.data.source.remote.response.detailcharacter.DetailAnimeCharaResponse
import com.amora.myseasonalanime.data.source.remote.response.trailer.TrailerResponse
import com.amora.myseasonalanime.data.source.remote.response.voiceactor.VoiceActorResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    @GET("seasons/{type}")
    suspend fun getAiringAnime(
        @Path("type") type: String,
        @Query("page") page: Int
    ): AiringResponse

    @GET("seasons/{type}")
    suspend fun getUpComingSeason(
        @Path("type") type: String,
        @Query("page") page: Int
    ): UpcomingResponse

    @GET("anime/{id}")
    suspend fun getAnimeId(@Path("id") id: Int): DetailAnimeResponse

    @GET("anime/{id}/characters")
    suspend fun getAnimeCharacters(@Path("id") id: Int): CharactersResponse

    @GET("characters/{id}")
    suspend fun getDetailAnimeCharacters(@Path("id") id: Int): DetailAnimeCharaResponse

    @GET("characters/{id}/voices")
    suspend fun getVoiceActor(@Path("id") id: Int): VoiceActorResponse

    @GET("anime/{id}/videos")
    suspend fun getAnimeTrailer(@Path("id") id: Int): TrailerResponse
}

