package com.amora.myseasonalanime.views.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amora.myseasonalanime.data.Repository
import com.amora.myseasonalanime.data.source.remote.response.anime.AnimeListResponse
import kotlinx.coroutines.launch

const val DEFAULT_PAGE = 1

class HomeViewModel(private val repository: Repository) : ViewModel() {

    private val _animeSeasonsNow = MutableLiveData<List<AnimeListResponse?>?>()
    val animeSeasonsNow: LiveData<List<AnimeListResponse?>?> = _animeSeasonsNow

    private val _upComingSeason = MutableLiveData<List<AnimeListResponse?>?>()
    val upComingSeason: LiveData<List<AnimeListResponse?>?> = _upComingSeason

    init {
        loadThisSeason()
    }

    private fun loadThisSeason() {

        viewModelScope.launch {
            try {
                _animeSeasonsNow.value = repository.getAnimeAiring(DEFAULT_PAGE)
                _upComingSeason.value = repository.getUpcomingSeason(DEFAULT_PAGE)
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }
}