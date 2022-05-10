package com.amora.myseasonalanime.data.source.paging.pagingAdapter

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
        binding.retryButton.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorMsg.text = loadState.error.localizedMessage
        }
        binding.progressBar.isVisible = loadState is LoadState.Loading && loadState.endOfPaginationReached
        binding.retryButton.isVisible = loadState is LoadState.Error
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