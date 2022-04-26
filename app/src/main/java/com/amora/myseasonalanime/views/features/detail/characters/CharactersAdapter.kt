package com.amora.myseasonalanime.views.features.detail.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amora.myseasonalanime.data.source.remote.response.characters.CharaItem
import com.amora.myseasonalanime.databinding.AnimeCharactersItemBinding
import com.amora.myseasonalanime.views.features.detail.trailer.TrailerAdapter


class CharactersAdapter(
    private val clickListener: CharactersAdapter.CharactersListener
) :
    ListAdapter<CharaItem, CharactersAdapter.CharactersViewHolder>(CharDiffCallback) {

    class CharactersViewHolder(
        private var binding: AnimeCharactersItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: CharactersListener, charaItem: CharaItem?) {
            binding.animeChara = charaItem
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                charaItem?.character.apply {
                    clickListener.onClick(this)
                }
            }
        }
    }

    class CharactersListener(val clickListener: (id: Int) -> Unit) {
        fun onClick(chara: CharaItem) = clickListener(chara)
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