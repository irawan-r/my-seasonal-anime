package com.amora.myseasonalanime.views.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amora.myseasonalanime.data.Repository
import com.amora.myseasonalanime.data.source.remote.response.anime.Anime
import com.amora.myseasonalanime.utils.enum.Misc
import com.amora.myseasonalanime.utils.enum.More
import kotlinx.coroutines.launch


class HomeViewModel(private val repository: Repository) : ViewModel() {

    private val _animeSeasonsNow = MutableLiveData<List<Anime?>?>()
    val animeSeasonsNow: LiveData<List<Anime?>?> = _animeSeasonsNow

    private val _upComingSeason = MutableLiveData<List<Anime?>?>()
    val upComingSeason: LiveData<List<Anime?>?> = _upComingSeason

    init {
        loadThisSeason()
    }

    private fun loadThisSeason() {
        val page = Misc.STARTING_PAGE_INDEX.item
        val type = More.AIRING.type

        viewModelScope.launch {
            try {
                _animeSeasonsNow.value = repository.getAnime(type, page)
                _upComingSeason.value =
                    repository.getAnime(More.UPCOMING.type, page)
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }
}