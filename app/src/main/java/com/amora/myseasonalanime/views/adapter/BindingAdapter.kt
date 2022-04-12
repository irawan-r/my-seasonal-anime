package com.amora.myseasonalanime.views.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amora.myseasonalanime.data.source.remote.response.detail.DataItem
import com.amora.myseasonalanime.views.features.home.HomeAdapter
import com.bumptech.glide.Glide

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<DataItem>?) {
    val adapter = recyclerView.adapter as HomeAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    Glide.with(imgView.context).load(imgUrl).into(imgView)
}