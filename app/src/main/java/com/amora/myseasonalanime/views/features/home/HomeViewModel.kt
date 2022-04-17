package com.amora.myseasonalanime.views.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amora.myseasonalanime.data.Repository
import com.amora.myseasonalanime.data.source.remote.response.animenow.AnimeListResponse
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository) : ViewModel() {

    private val _animeSeasonsNow = MutableLiveData<List<AnimeListResponse>>()
    val animeSeasonsNow: LiveData<List<AnimeListResponse>> = _animeSeasonsNow

    init {
        loadThisSeason()
    }

    private fun loadThisSeason() {
        viewModelScope.launch {
            try {
                _animeSeasonsNow.value = repository.getSeasonsNow()
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }
}