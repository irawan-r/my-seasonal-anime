package com.amora.myseasonalanime.views.features.detail.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amora.myseasonalanime.data.source.remote.response.characters.CharaItems
import com.amora.myseasonalanime.databinding.AnimeCharactersItemBinding


class CharactersAdapter(private val clickListener: CharactersListener) :
    ListAdapter<CharaItems, CharactersAdapter.CharactersViewHolder>(CharDiffCallback) {

    class CharactersViewHolder(
        private var binding: AnimeCharactersItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: CharactersListener, charaItems: CharaItems?) {
            binding.animeChara = charaItems
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                charaItems?.character?.malId.apply {
                    this?.let { it -> clickListener.clickId(it) }
                }
            }
        }
    }

    class CharactersListener(val clickListener: (id: Int) -> Unit) {
        fun clickId(id: Int) = clickListener(id)
    }

    object CharDiffCallback : DiffUtil.ItemCallback<CharaItems?>() {

        override fun areItemsTheSame(oldItems: CharaItems, newItems: CharaItems): Boolean {
            return oldItems.character == newItems.character
        }

        override fun areContentsTheSame(oldItems: CharaItems, newItems: CharaItems): Boolean {
            return oldItems == newItems
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