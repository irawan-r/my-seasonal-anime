package com.amora.myseasonalanime.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amora.myseasonalanime.data.source.remote.response.characters.CharaItem
import com.amora.myseasonalanime.databinding.AnimeCharactersItemBinding


class CharactersAdapter :
    ListAdapter<CharaItem, CharactersAdapter.CharactersViewHolder>(CharDiffCallback) {

    class CharactersViewHolder(
        private var binding: AnimeCharactersItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(charaItem: CharaItem?) {
            binding.animeChara = charaItem
            binding.executePendingBindings()
        }
    }

    object CharDiffCallback : DiffUtil.ItemCallback<CharaItem?>() {
        override fun areItemsTheSame(oldItem: CharaItem, newItem: CharaItem): Boolean {
            return oldItem.character?.malId == newItem.character?.malId
        }

        override fun areContentsTheSame(oldItem: CharaItem, newItem: CharaItem): Boolean {
            return oldItem.character?.images == newItem.character?.images
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CharactersViewHolder(
            AnimeCharactersItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        val chara = getItem(position)
        holder.bind(chara)
    }
}