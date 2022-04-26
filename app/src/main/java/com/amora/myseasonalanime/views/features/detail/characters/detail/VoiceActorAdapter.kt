package com.amora.myseasonalanime.views.features.detail.characters.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amora.myseasonalanime.data.source.remote.response.characters.CharaItem
import com.amora.myseasonalanime.data.source.remote.response.detailcharacter.DetailCharaItem
import com.amora.myseasonalanime.databinding.CharactersDialogBinding

class VoiceActorAdapter :
    ListAdapter<CharaItem, VoiceActorAdapter.DetailCharaViewHolder>(DetailCharaDiffCallback) {
    class DetailCharaViewHolder(
        private var binding: CharactersDialogBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CharaItem) {
            binding.chara = data
            binding.executePendingBindings()
        }
    }

    object DetailCharaDiffCallback : DiffUtil.ItemCallback<CharaItem>() {
        override fun areItemsTheSame(oldItem: CharaItem, newItem: CharaItem): Boolean {
            return oldItem.voiceActors == newItem.voiceActors
        }

        override fun areContentsTheSame(
            oldItem: CharaItem, newItem: CharaItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): DetailCharaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DetailCharaViewHolder(
            CharactersDialogBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DetailCharaViewHolder, position: Int) {
        val voiceActor = getItem(position)
        holder.bind(voiceActor)
    }
}