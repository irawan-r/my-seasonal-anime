package com.amora.myseasonalanime.views.features.detail.characters.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amora.myseasonalanime.data.Repository
import com.amora.myseasonalanime.data.source.remote.response.detailcharacter.DetailCharaItem
import com.amora.myseasonalanime.data.source.remote.response.voiceactor.VoiceActors
import kotlinx.coroutines.launch

class DetailCharaViewModel(private val repository: Repository) : ViewModel() {

    private val _voiceActor = MutableLiveData<List<VoiceActors?>?>()
    val voiceActors: LiveData<List<VoiceActors?>?> = _voiceActor

    private val _detailChara = MutableLiveData<DetailCharaItem?>()
    val detailCharaItem: LiveData<DetailCharaItem?> = _detailChara

    fun setDetailChar(id: Int) {
        viewModelScope.launch {
            try {
                _detailChara.value = repository.getDetailChara(id)?.data
                _voiceActor.value = repository.getVoiceActor(id)
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }
}