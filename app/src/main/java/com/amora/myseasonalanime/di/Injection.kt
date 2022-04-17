package com.amora.myseasonalanime.di

import android.content.Context
import com.amora.myseasonalanime.data.Repository
import com.amora.myseasonalanime.data.source.RemoteDataSource
import com.amora.myseasonalanime.data.source.remote.api.ApiConfig

object Injection {
    fun provideRepository(): Repository {
        val api = ApiConfig
        val remoteDataSource = RemoteDataSource.getInstance(api)

        return Repository.getInstance(remoteDataSource)
    }
}