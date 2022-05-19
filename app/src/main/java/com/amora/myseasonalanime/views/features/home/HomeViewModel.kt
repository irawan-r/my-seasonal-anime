package com.amora.myseasonalanime.views.features.home

import androidx.lifecycle.*
import com.amora.myseasonalanime.data.Repository
import com.amora.myseasonalanime.data.source.remote.response.anime.Anime
import com.amora.myseasonalanime.utils.enum.Misc
import com.amora.myseasonalanime.utils.enum.More
import kotlinx.coroutines.launch


class HomeViewModel(
    private val repository: Repository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _animeSeasonsNow = MutableLiveData<List<Anime?>?>()
    val animeSeasonsNow: LiveData<List<Anime?>?> = _animeSeasonsNow

    private val _upComingSeason = MutableLiveData<List<Anime?>?>()
    val upComingSeason: LiveData<List<Anime?>?> = _upComingSeason

    init {
        getAiringAnime()
    }

    private fun getAiringAnime() {
        val page = Misc.STARTING_PAGE_INDEX.item
        val airing = More.AIRING.type
        val upcoming = More.UPCOMING.type

        viewModelScope.launch {
            try {
                _animeSeasonsNow.value = repository.getAnime(airing, page)
                _upComingSeason.value = repository.getAnime(upcoming, page)
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }
}