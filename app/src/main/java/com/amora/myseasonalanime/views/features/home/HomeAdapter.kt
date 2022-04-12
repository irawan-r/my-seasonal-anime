package com.amora.myseasonalanime.views.features.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amora.myseasonalanime.data.source.remote.response.detail.DetailItem
import com.amora.myseasonalanime.databinding.AnimeListItemBinding

class HomeAdapter :
    ListAdapter<DetailItem, HomeAdapter.CurrentSeasonViewHolder>(DiffCallback) {

    class CurrentSeasonViewHolder(
        private var binding: AnimeListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dataItem: DetailItem) {
            binding.animeData = dataItem
            binding.executePendingBindings()
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<DetailItem>() {
        override fun areItemsTheSame(oldItem: DetailItem, newItem: DetailItem): Boolean {
            return oldItem.malId == newItem.malId
        }

        override fun areContentsTheSame(oldItem: DetailItem, newItem: DetailItem): Boolean {
            return oldItem.images == newItem.images
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentSeasonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CurrentSeasonViewHolder(
            AnimeListItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CurrentSeasonViewHolder, position: Int) {
        val anime = getItem(position)
        holder.bind(anime)
    }
}