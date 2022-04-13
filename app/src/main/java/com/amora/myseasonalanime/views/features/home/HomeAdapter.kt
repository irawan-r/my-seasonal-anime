package com.amora.myseasonalanime.views.features.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amora.myseasonalanime.R
import com.amora.myseasonalanime.data.source.remote.response.detail.DetailItem
import com.amora.myseasonalanime.databinding.AnimeListItemBinding

class HomeAdapter :
    ListAdapter<DetailItem, HomeAdapter.CurrentSeasonViewHolder>(DiffCallback) {

    class CurrentSeasonViewHolder(private var binding: AnimeListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
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
        val view: AnimeListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.anime_list_item,
            parent,
            false
        )
        return CurrentSeasonViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurrentSeasonViewHolder, position: Int) {
        val anime = getItem(position)
        holder.bind(anime)
    }
}