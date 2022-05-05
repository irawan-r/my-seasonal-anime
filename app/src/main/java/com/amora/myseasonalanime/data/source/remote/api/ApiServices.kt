package com.amora.myseasonalanime.data.source.remote.api

import com.amora.myseasonalanime.data.source.remote.response.anime.AnimeListResponse
import com.amora.myseasonalanime.data.source.remote.response.characters.CharactersResponse
import com.amora.myseasonalanime.data.source.remote.response.detailanime.DetailAnimeResponse
import com.amora.myseasonalanime.data.source.remote.response.detailcharacter.DetailAnimeCharaResponse
import com.amora.myseasonalanime.data.source.remote.response.trailer.TrailerResponse
import com.amora.myseasonalanime.data.source.remote.response.voiceactor.VoiceActorResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    @GET("anime")
    suspend fun searchAnime(
        @Query("page") page: Int
    ): AnimeListResponse

    @GET("seasons/{year}/{seasons}")
    suspend fun getAnimeSeasons(
        @Path("year") year: Int,
        @Path("seasons") seasons: String,
        @Query("page") page: Int
    ): AnimeListResponse

    @GET("seasons/{type}")
    suspend fun getAnime(
        @Path("type") type: String,
        @Query("page") page: Int
    ): AnimeListResponse

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

