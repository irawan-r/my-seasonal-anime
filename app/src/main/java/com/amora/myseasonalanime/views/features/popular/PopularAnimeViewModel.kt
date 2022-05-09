package com.amora.myseasonalanime.views.features.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.amora.myseasonalanime.data.Repository
import com.amora.myseasonalanime.data.source.remote.response.anime.Anime
import com.amora.myseasonalanime.utils.enum.Filter
import com.amora.myseasonalanime.utils.enum.Misc
import kotlinx.coroutines.flow.Flow

class PopularAnimeViewModel(private val repository: Repository) : ViewModel() {

    init {
        val type = Filter.AIRING.filter
        val page = Misc.STARTING_PAGE_INDEX.item
        topAnime(type, page)
    }

    fun topAnime(filter: String, page: Int): Flow<PagingData<Anime>> {
        return repository.getTopAnime(filter, page)
            .cachedIn(viewModelScope)
    }
}