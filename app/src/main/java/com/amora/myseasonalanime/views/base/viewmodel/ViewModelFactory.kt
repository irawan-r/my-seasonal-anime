package com.amora.myseasonalanime.views.base.viewmodel

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.amora.myseasonalanime.data.Repository
import com.amora.myseasonalanime.views.features.detail.DetailViewModel
import com.amora.myseasonalanime.views.features.detail.characters.detail.DetailCharaViewModel
import com.amora.myseasonalanime.views.features.home.HomeViewModel
import com.amora.myseasonalanime.views.features.more.MoreAnimeViewModel
import com.amora.myseasonalanime.views.features.popular.PopularAnimeViewModel
import com.amora.myseasonalanime.views.features.search.SearchViewModel

class ViewModelFactory(
    private val repository: Repository,
    owner: SavedStateRegistryOwner
) : AbstractSavedStateViewModelFactory(owner, null) {

    /*companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(repository: Repository, owner: SavedStateRegistryOwner): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(repository, owner)
            }
    }*/

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle,
    ): T {
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

            modelClass.isAssignableFrom(SearchViewModel::class.java) -> {
                SearchViewModel(repository, handle) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}