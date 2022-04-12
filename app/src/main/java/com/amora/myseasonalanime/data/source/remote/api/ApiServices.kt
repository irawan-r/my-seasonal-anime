package com.amora.myseasonalanime.data.source.remote.api

import com.amora.myseasonalanime.data.source.remote.response.characters.Characters
import com.amora.myseasonalanime.data.source.remote.response.detail.AnimeResponse
import com.amora.myseasonalanime.data.source.remote.response.detail.DetailItem
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {
    @GET("seasons/now")
    suspend fun getSeasonNow(): AnimeResponse

    @GET("anime/{id}")
    suspend fun getAnimeId(@Path("id") id: Int): DetailItem

    @GET("anime/{id}/characters")
    suspend fun getAnimeCharacters(@Path("id")id: Int): Characters
}

