package com.amora.myseasonalanime.views.features.more.loadstate

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class ReposLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<ReposLoadStatesViewHolder>() {
    override fun onBindViewHolder(holder: ReposLoadStatesViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState,
    ): ReposLoadStatesViewHolder {
        return ReposLoadStatesViewHolder.create(parent, retry)
    }
}