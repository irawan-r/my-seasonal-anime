package com.amora.myseasonalanime.data.source.remote.api

import com.amora.myseasonalanime.data.source.remote.response.AnimeResponse
import retrofit2.http.GET

interface ApiServices {
    @GET("seasons/now")
    suspend fun getSeasonNow(): AnimeResponse
}

