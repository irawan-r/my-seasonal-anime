package com.amora.myseasonalanime.views.features.more.loadstate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.amora.myseasonalanime.databinding.PagingLoadingBinding

class ReposLoadStatesViewHolder(
    private val binding: PagingLoadingBinding,
    retry: () -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

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
        fun create(parent: ViewGroup, retry: () -> Unit): ReposLoadStatesViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            return ReposLoadStatesViewHolder(
                PagingLoadingBinding.inflate(layoutInflater, parent, false), retry
            )
        }
    }
}