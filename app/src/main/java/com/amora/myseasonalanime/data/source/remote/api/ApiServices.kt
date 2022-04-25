package com.amora.myseasonalanime.data.source.remote.api

import com.amora.myseasonalanime.data.source.remote.response.animenow.AnimeResponse
import com.amora.myseasonalanime.data.source.remote.response.characters.CharactersResponse
import com.amora.myseasonalanime.data.source.remote.response.detail.DetailAnimeResponse
import com.amora.myseasonalanime.data.source.remote.response.trailer.TrailerResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    @GET("seasons/now")
    suspend fun getAiringAnime(@Query("page") page: Int): AnimeResponse

    @GET("top/anime")
    suspend fun getTopAnime(@Query("page") page: Int): AnimeResponse

    @GET("anime/{id}")
    suspend fun getAnimeId(@Path("id") id: Int): DetailAnimeResponse

    @GET("anime/{id}/characters")
    suspend fun getAnimeCharacters(@Path("id") id: Int): CharactersResponse

    @GET("anime/{id}/videos")
    suspend fun getAnimeTrailer(@Path("id") id : Int): TrailerResponse
}

