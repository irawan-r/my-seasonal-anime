package com.amora.myseasonalanime.views.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amora.myseasonalanime.data.Repository
import com.amora.myseasonalanime.data.source.remote.api.ApiConfig
import com.amora.myseasonalanime.data.source.remote.response.detail.DataItem
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository) : ViewModel() {

    private val _thisSeason = MutableLiveData<List<DataItem>>()
    val thisSeason: LiveData<List<DataItem>> = _thisSeason

    init {
        viewModelScope.launch {
            try {
                _thisSeason.value = repository.getSeasonNow()
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }
}