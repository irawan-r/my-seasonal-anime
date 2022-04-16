package com.amora.myseasonalanime.views.features.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amora.myseasonalanime.data.source.remote.response.animenow.AnimeListResponse
import com.amora.myseasonalanime.databinding.AnimeListItemBinding

class HomeAdapter(private val clickListener: AnimeListener) :
    ListAdapter<AnimeListResponse, HomeAdapter.CurrentSeasonViewHolder>(DiffCallback) {

    class CurrentSeasonViewHolder(private var binding: AnimeListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: AnimeListener, data: AnimeListResponse) {
            binding.anime = data
            binding.root.setOnClickListener {
                data.malId?.apply {
                    clickListener.onClick(this)
                }
            }
            binding.executePendingBindings()
        }
    }

    class AnimeListener(val clickListener: (animeId: Int) -> Unit) {
        fun onClick(animeId: Int) = clickListener(animeId)
    }

    object DiffCallback : DiffUtil.ItemCallback<AnimeListResponse>() {
        override fun areItemsTheSame(oldItem: AnimeListResponse, newItem: AnimeListResponse): Boolean {
            return oldItem.malId == newItem.malId
        }

        override fun areContentsTheSame(oldItem: AnimeListResponse, newItem: AnimeListResponse): Boolean {
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
        holder.bind(clickListener, anime)
    }
}