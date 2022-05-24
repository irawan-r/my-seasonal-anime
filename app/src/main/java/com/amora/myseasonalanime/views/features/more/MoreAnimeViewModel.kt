package com.amora.myseasonalanime.views.features.more

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.amora.myseasonalanime.data.Repository
import com.amora.myseasonalanime.data.model.popular.Anime
import kotlinx.coroutines.flow.Flow

class MoreAnimeViewModel(
    private val repository: Repository
) : ViewModel() {

    fun animeLoadMore(type: String, page: Int): Flow<PagingData<Anime>> {
        return repository.getMoreAnime(type, page)
            .cachedIn(viewModelScope)
    }
}
