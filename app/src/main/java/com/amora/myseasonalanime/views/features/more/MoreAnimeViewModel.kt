package com.amora.myseasonalanime.views.features.more

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.amora.myseasonalanime.data.DataSource
import com.amora.myseasonalanime.data.Repository
import com.amora.myseasonalanime.data.source.STARTING_PAGE_INDEX
import com.amora.myseasonalanime.data.source.remote.response.animenow.AnimeListResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MoreAnimeViewModel(
    private val repository: Repository
) : ViewModel() {

    fun loadData(): Flow<PagingData<AnimeListResponse>> {
        return repository.getMoreAnime(STARTING_PAGE_INDEX)
            .cachedIn(viewModelScope)
    }
}
