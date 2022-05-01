package com.amora.myseasonalanime.views.features.more

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.amora.myseasonalanime.data.source.remote.response.anime.AnimeListResponse
import com.amora.myseasonalanime.databinding.AnimeMoreListItemBinding

class MoreAnimeAdapter(private val clickListener: AnimeListener) :
    PagingDataAdapter<AnimeListResponse, MoreAnimeAdapter.MoreAnimeViewHolder>(DiffCallback) {

    class MoreAnimeViewHolder(private var binding: AnimeMoreListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: AnimeListener, data: AnimeListResponse?) {
            binding.animeDetail = data
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                data?.malId?.apply {
                    clickListener.onClick(this)
                }
            }
        }
    }

    class AnimeListener(val clickListener: (animeId: Int) -> Unit) {
        fun onClick(animeId: Int) = clickListener(animeId)
    }

    object DiffCallback : DiffUtil.ItemCallback<AnimeListResponse>() {
        override fun areItemsTheSame(
            oldItem: AnimeListResponse,
            newItem: AnimeListResponse,
        ): Boolean {
            return oldItem.malId == newItem.malId
        }

        override fun areContentsTheSame(
            oldItem: AnimeListResponse,
            newItem: AnimeListResponse,
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MoreAnimeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MoreAnimeViewHolder(
            AnimeMoreListItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MoreAnimeViewHolder, position: Int) {
        val anime = getItem(position)
        holder.bind(clickListener, anime)
    }
}