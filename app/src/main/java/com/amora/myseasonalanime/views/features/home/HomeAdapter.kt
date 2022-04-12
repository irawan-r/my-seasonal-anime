package com.amora.myseasonalanime.views.features.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amora.myseasonalanime.data.source.remote.response.detail.DataItem
import com.amora.myseasonalanime.databinding.AnimeListItemBinding

class HomeAdapter :
    ListAdapter<DataItem, HomeAdapter.CurrentSeasonViewHolder>(DiffCallback) {

    private var listData = ArrayList<DataItem>()

    fun setData(newData: List<DataItem>?) {
        if (newData == null) return
        listData.clear()
        listData.addAll(newData)
    }

    class CurrentSeasonViewHolder(
        private var binding: AnimeListItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dataItem: DataItem) {
            binding.animeData = dataItem
            binding.executePendingBindings()
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem.malId == newItem.malId
        }

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem.images == newItem.images
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentSeasonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CurrentSeasonViewHolder(
            AnimeListItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CurrentSeasonViewHolder, position: Int) {
        val anime = getItem(position)
        holder.bind(anime)
    }
}