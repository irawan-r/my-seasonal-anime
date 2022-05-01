package com.amora.myseasonalanime.views.features.more

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.amora.myseasonalanime.data.Repository
import com.amora.myseasonalanime.data.source.STARTING_PAGE_INDEX
import com.amora.myseasonalanime.data.source.remote.response.anime.AnimeListResponse
import kotlinx.coroutines.flow.Flow

class MoreAnimeViewModel(
    private val repository: Repository,
) : ViewModel() {

    fun airingLoadMore(): Flow<PagingData<AnimeListResponse>> {
        return repository.getMoreAiring(STARTING_PAGE_INDEX)
            .cachedIn(viewModelScope)
    }

    fun upcomingLoadMore(): Flow<PagingData<AnimeListResponse>> {
        return repository.getMoreUpcoming(STARTING_PAGE_INDEX)
            .cachedIn(viewModelScope)
    }
}
