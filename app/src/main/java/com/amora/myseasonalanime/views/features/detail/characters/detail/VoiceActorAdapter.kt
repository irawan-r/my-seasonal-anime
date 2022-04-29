package com.amora.myseasonalanime.views.features.detail.characters.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amora.myseasonalanime.data.source.remote.response.voiceactor.DataItem
import com.amora.myseasonalanime.databinding.VaListItemBinding

class VoiceActorAdapter :
    ListAdapter<DataItem, VoiceActorAdapter.DetailCharaViewHolder>(DetailCharaDiffCallback) {

    class DetailCharaViewHolder(
        private var binding: VaListItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataItem) {
            binding.voiceAct = data
            binding.executePendingBindings()
        }
    }

    object DetailCharaDiffCallback : DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem.person == newItem.person
        }

        override fun areContentsTheSame(
            oldItem: DataItem, newItem: DataItem,
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
            VaListItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DetailCharaViewHolder, position: Int) {
        val voiceActor = getItem(position)
        holder.bind(voiceActor)
    }
}