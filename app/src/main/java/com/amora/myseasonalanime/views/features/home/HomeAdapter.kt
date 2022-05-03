package com.amora.myseasonalanime.views.features.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amora.myseasonalanime.data.source.remote.response.anime.Anime
import com.amora.myseasonalanime.databinding.AnimeListItemBinding

class HomeAdapter(private val clickListener: AnimeListener) :
    ListAdapter<Anime, HomeAdapter.CurrentSeasonViewHolder>(DiffCallback) {

    class CurrentSeasonViewHolder(private var binding: AnimeListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: AnimeListener, data: Anime) {
            binding.animeDetail = data
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                data.malId?.apply {
                    clickListener.onClick(this)
                }
            }
        }
    }

    class AnimeListener(val clickListener: (animeId: Int) -> Unit) {
        fun onClick(animeId: Int) = clickListener(animeId)
    }

    object DiffCallback : DiffUtil.ItemCallback<Anime>() {
        override fun areItemsTheSame(
            oldItem: Anime,
            newItem: Anime,
        ): Boolean {
            return oldItem.malId == newItem.malId
        }

        override fun areContentsTheSame(
            oldItem: Anime,
            newItem: Anime,
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CurrentSeasonViewHolder {
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