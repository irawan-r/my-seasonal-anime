package com.amora.myseasonalanime.views.features.detail.characters.detail.loadstate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.amora.myseasonalanime.databinding.PagingLoadingBinding
import com.amora.myseasonalanime.views.features.more.loadstate.ReposLoadStatesViewHolder

class DetailCharLoadStateViewHolder(
    private val binding: PagingLoadingBinding,
    retry: () -> Unit
): RecyclerView.ViewHolder(binding.root) {

    init {
        binding.loadmoreRetry.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorMsg.text = loadState.error.localizedMessage
        }
        binding.loadmoreProgress.isVisible = loadState is LoadState.Loading
        binding.loadmoreRetry.isVisible = loadState is LoadState.Error
        binding.errorMsg.isVisible = loadState is LoadState.Error
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): DetailCharLoadStateViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            return DetailCharLoadStateViewHolder(
                PagingLoadingBinding.inflate(layoutInflater, parent, false), retry
            )
        }
    }
}
