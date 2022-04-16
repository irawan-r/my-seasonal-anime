package com.amora.myseasonalanime.views.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amora.myseasonalanime.data.Repository
import com.amora.myseasonalanime.data.source.remote.response.detail.DetailItem
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository) : ViewModel() {

    private val _animeSeasonsNow = MutableLiveData<List<DetailItem>>()
    val animeSeasonsNow: LiveData<List<DetailItem>> = _animeSeasonsNow

    private val _animeSeasonNow = MutableLiveData<Int>()
    val animeSeasonNow: LiveData<Int> = _animeSeasonNow

    init {
        viewModelScope.launch {
            try {
                _animeSeasonsNow.value = repository.getSeasonsNow()
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }

    fun onAnimeClicked(animeId: Int) {
        _animeSeasonNow.value = animeId
    }
}