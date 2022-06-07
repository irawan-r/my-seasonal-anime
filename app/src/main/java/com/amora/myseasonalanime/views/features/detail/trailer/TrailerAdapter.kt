package com.amora.myseasonalanime.views.features.detail.trailer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amora.myseasonalanime.data.source.remote.response.trailer.TrailerItem
import com.amora.myseasonalanime.databinding.AnimeTrailerItemBinding

class TrailerAdapter(private val clickListener: TrailerListener) :
    ListAdapter<TrailerItem, TrailerAdapter.TrailerViewHolder>(TrailerDiffCallback) {

    object TrailerDiffCallback : DiffUtil.ItemCallback<TrailerItem>() {
        override fun areItemsTheSame(oldItem: TrailerItem, newItem: TrailerItem): Boolean {
            return oldItem.trailer == newItem.trailer
        }

        override fun areContentsTheSame(oldItem: TrailerItem, newItem: TrailerItem): Boolean {
            return oldItem == newItem
        }
    }

    class TrailerViewHolder(
        private var binding: AnimeTrailerItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: TrailerListener, data: TrailerItem) {
            binding.anime = data
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                data.trailer?.embedUrl.apply {
                    this?.let { it -> clickListener.onClick(it) }
                }
            }
        }
    }

    class TrailerListener(val clickListener: (url: String) -> Unit) {
        fun onClick(url: String) = clickListener(url)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): TrailerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TrailerViewHolder(
            AnimeTrailerItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TrailerViewHolder, position: Int) {
        val trailer = getItem(position)
        holder.bind(clickListener, trailer)
    }
}