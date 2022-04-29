package com.amora.myseasonalanime.views.features.detail.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amora.myseasonalanime.data.source.remote.response.characters.CharaItem
import com.amora.myseasonalanime.databinding.AnimeCharactersItemBinding


class CharactersAdapter(private val clickListener: CharactersListener) :
    ListAdapter<CharaItem, CharactersAdapter.CharactersViewHolder>(CharDiffCallback) {

    class CharactersViewHolder(
        private var binding: AnimeCharactersItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: CharactersListener, charaItem: CharaItem?) {
            binding.animeChara = charaItem
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                charaItem?.character?.malId.apply {
                    this?.let { it -> clickListener.clickId(it) }
                }
            }
        }
    }

    class CharactersListener(val clickListener: (id: Int) -> Unit) {
        fun clickId(id: Int) = clickListener(id)
    }

    object CharDiffCallback : DiffUtil.ItemCallback<CharaItem?>() {

        override fun areItemsTheSame(oldItem: CharaItem, newItem: CharaItem): Boolean {
            return oldItem.character == newItem.character
        }

        override fun areContentsTheSame(oldItem: CharaItem, newItem: CharaItem): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CharactersAdapter.CharactersViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CharactersAdapter.CharactersViewHolder(
            AnimeCharactersItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        val chara = getItem(position)
        holder.bind(clickListener, chara)
    }
}