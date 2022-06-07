package com.amora.myseasonalanime.views.features.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.amora.myseasonalanime.data.model.search.AnimeSearch
import com.amora.myseasonalanime.databinding.AnimeSearchListItemBinding

class SearchAnimeAdapter(private val clickListener: AnimeListener) :
    PagingDataAdapter<UiModel, SearchAnimeAdapter.SearchAnimeViewHolder>(DiffCallback) {

    class SearchAnimeViewHolder(private var binding: AnimeSearchListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: AnimeListener, data: AnimeSearch?) {
            binding.animeDetail = data
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                data?.malId?.apply {
                    clickListener.onClick(this)
                }
            }
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<UiModel>() {

        override fun areItemsTheSame(oldItem: UiModel, newItem: UiModel): Boolean {
            return (oldItem is UiModel.RepoItem && newItem is UiModel.RepoItem &&
                    oldItem.repo.malId == newItem.repo.malId)
        }

        override fun areContentsTheSame(oldItem: UiModel, newItem: UiModel): Boolean =
            oldItem == newItem

    }

    class AnimeListener(val clickListener: (animeId: Int) -> Unit) {
        fun onClick(animeId: Int) = clickListener(animeId)
    }

    override fun onBindViewHolder(holder: SearchAnimeViewHolder, position: Int) {
        val uiModel = getItem(position)
        uiModel.let {
            if (uiModel is UiModel.RepoItem) holder.bind(clickListener, uiModel.repo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAnimeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SearchAnimeViewHolder(
            AnimeSearchListItemBinding.inflate(layoutInflater, parent, false)
        )
    }


}