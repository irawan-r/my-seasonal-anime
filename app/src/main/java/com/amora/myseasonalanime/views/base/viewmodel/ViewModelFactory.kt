package com.amora.myseasonalanime.views.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amora.myseasonalanime.data.Repository
import com.amora.myseasonalanime.di.Injection
import com.amora.myseasonalanime.views.features.detail.DetailViewModel
import com.amora.myseasonalanime.views.features.detail.characters.detail.DetailCharaViewModel
import com.amora.myseasonalanime.views.features.home.HomeViewModel
import com.amora.myseasonalanime.views.features.more.MoreAnimeViewModel
import com.amora.myseasonalanime.views.features.popular.PopularAnimeViewModel

class ViewModelFactory(private val repository: Repository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository())
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(repository) as T
            }

            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(repository) as T
            }

            modelClass.isAssignableFrom(DetailCharaViewModel::class.java) -> {
                DetailCharaViewModel(repository) as T
            }

            modelClass.isAssignableFrom(MoreAnimeViewModel::class.java) -> {
                MoreAnimeViewModel(repository) as T
            }

            modelClass.isAssignableFrom(PopularAnimeViewModel::class.java) -> {
                PopularAnimeViewModel(repository) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}