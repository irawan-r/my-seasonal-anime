package com.amora.myseasonalanime.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amora.myseasonalanime.data.source.remote.response.detail.GenresItem
import com.amora.myseasonalanime.databinding.GenresItemBinding

class GenresAdapter :
    ListAdapter<GenresItem, GenresAdapter.GenresViewHolder>(GenresDiffCallback) {
    class GenresViewHolder(
        private var binding: GenresItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(genresItem: GenresItem) {
            binding.animeGenres = genresItem
            binding.executePendingBindings()
        }
    }

    object GenresDiffCallback: DiffUtil.ItemCallback<GenresItem>() {
        override fun areItemsTheSame(oldItem: GenresItem, newItem: GenresItem): Boolean {
            return oldItem.malId == newItem.malId
        }

        override fun areContentsTheSame(oldItem: GenresItem, newItem: GenresItem): Boolean {
            return oldItem.name == newItem.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return GenresViewHolder(
            GenresItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: GenresViewHolder, position: Int) {
        val genres = getItem(position)
        holder.bind(genres)
    }
}