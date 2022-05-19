package com.amora.myseasonalanime.views.features.detail

import androidx.lifecycle.*
import com.amora.myseasonalanime.data.Repository
import com.amora.myseasonalanime.data.source.remote.response.characters.CharaItems
import com.amora.myseasonalanime.data.source.remote.response.detailanime.DetailAnime
import com.amora.myseasonalanime.data.source.remote.response.trailer.TrailerItem
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: Repository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _detailAnime = MutableLiveData<DetailAnime>()
    val detailAnime: LiveData<DetailAnime> = _detailAnime

    private val _charaAnime = MutableLiveData<List<CharaItems?>?>()
    val charaAnime: LiveData<List<CharaItems?>?> = _charaAnime

    private val _trailerAnime = MutableLiveData<List<TrailerItem?>?>()
    val trailerAnime: LiveData<List<TrailerItem?>?> = _trailerAnime

    fun setDetailAnime(id: Int) {
        viewModelScope.launch {
            try {
                _detailAnime.value = repository.getAnimeId(id).data
                _charaAnime.value = repository.getAnimeChara(id)
                _trailerAnime.value = repository.getAnimeTrailer(id)
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }
}