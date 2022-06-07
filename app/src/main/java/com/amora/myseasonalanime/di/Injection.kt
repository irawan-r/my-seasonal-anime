package com.amora.myseasonalanime.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import com.amora.myseasonalanime.data.Repository
import com.amora.myseasonalanime.data.db.RepoDatabase
import com.amora.myseasonalanime.data.source.RemoteDataSource
import com.amora.myseasonalanime.data.source.remote.api.ApiConfig
import com.amora.myseasonalanime.views.base.viewmodel.ViewModelFactory

object Injection {
    private fun provideRepository(context: Context): Repository {
        val database = RepoDatabase.getInstance(context)
        val remoteDataSource =
            RemoteDataSource.getInstance(ApiConfig, database)

        return Repository.getInstance(remoteDataSource)
    }

    fun provideViewModelFactory(
        context: Context,
        owner: SavedStateRegistryOwner,
    ): ViewModelProvider.Factory {
        return ViewModelFactory(provideRepository(context), owner)
    }
}