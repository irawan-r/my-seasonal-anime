package com.amora.myseasonalanime.views.features.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.amora.myseasonalanime.data.source.remote.response.anime.Anime
import com.amora.myseasonalanime.databinding.AnimeMoreListItemBinding

class PopularAnimeAdapter(private val clickListener: AnimeListener) :
    PagingDataAdapter<Anime, PopularAnimeAdapter.PopularAnimeViewHolder>(DiffCallback) {

    class PopularAnimeViewHolder(private var binding: AnimeMoreListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: AnimeListener, data: Anime?) {
            binding.animeDetail = data
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                data?.malId?.apply {
                    clickListener.onClick(this)
                }
            }
        }
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

    class AnimeListener(val clickListener: (animeId: Int) -> Unit) {
        fun onClick(animeId: Int) = clickListener(animeId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularAnimeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PopularAnimeViewHolder(
            AnimeMoreListItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PopularAnimeViewHolder, position: Int) {
        val anime = getItem(position)
        holder.bind(clickListener, anime)
    }
}