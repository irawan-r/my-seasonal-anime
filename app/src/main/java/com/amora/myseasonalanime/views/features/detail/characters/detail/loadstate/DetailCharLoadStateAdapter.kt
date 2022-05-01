package com.amora.myseasonalanime.views.features.detail.characters.detail.loadstate

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class DetailCharLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<DetailCharLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: DetailCharLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState,
    ): DetailCharLoadStateViewHolder {
        return DetailCharLoadStateViewHolder.create(parent, retry)
    }
}