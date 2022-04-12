package com.amora.myseasonalanime.views.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amora.myseasonalanime.data.source.remote.response.detail.DetailItem
import com.amora.myseasonalanime.views.features.home.HomeAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.BlurTransformation

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<DetailItem>?) {
    val adapter = recyclerView.adapter as HomeAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    Glide.with(imgView.context).load(imgUrl).into(imgView)
}

@BindingAdapter("showBlurImage")
fun blurImage(imgView: ImageView, imgUrl: String?) {
    Glide.with(imgView.context).load(imgUrl)
        .apply(RequestOptions.bitmapTransform(BlurTransformation(25, 3))).into(imgView)
}