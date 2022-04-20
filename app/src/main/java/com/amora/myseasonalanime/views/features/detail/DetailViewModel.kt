package com.amora.myseasonalanime.views.features.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amora.myseasonalanime.data.Repository
import com.amora.myseasonalanime.data.source.remote.response.characters.CharaItem
import com.amora.myseasonalanime.data.source.remote.response.detail.DetailAnime
import com.amora.myseasonalanime.data.source.remote.response.detail.GenresItem
import com.amora.myseasonalanime.data.source.remote.response.detail.Trailer
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: Repository) : ViewModel() {

    private val _detailAnime = MutableLiveData<DetailAnime>()
    val detailAnime: LiveData<DetailAnime> = _detailAnime

    private val _charaAnime = MutableLiveData<List<CharaItem?>?>()
    val charaAnime: LiveData<List<CharaItem?>?> = _charaAnime


    fun setDetailAnime(id: Int) {
        viewModelScope.launch {
            try {
                _detailAnime.value = repository.getAnimeId(id).data
                _charaAnime.value = repository.getAnimeChara(id)
//                _trailerAnime.value = repository.getTrailer(id)
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }
}