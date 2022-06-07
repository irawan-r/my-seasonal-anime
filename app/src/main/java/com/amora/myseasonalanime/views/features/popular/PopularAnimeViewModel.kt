package com.amora.myseasonalanime.views.features.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.amora.myseasonalanime.data.Repository
import com.amora.myseasonalanime.data.model.popular.Anime
import kotlinx.coroutines.flow.*

class PopularAnimeViewModel(
    private val repository: Repository
) : ViewModel() {

    fun topAnime(filter: String): Flow<PagingData<Anime>> {
        return repository.getPopularAnime(filter)
            .cachedIn(viewModelScope)
    }
}