package com.amora.myseasonalanime.views.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amora.myseasonalanime.data.source.remote.response.animenow.AnimeListResponse
import com.amora.myseasonalanime.data.source.remote.response.characters.CharaItem
import com.amora.myseasonalanime.data.source.remote.response.trailer.Trailer
import com.amora.myseasonalanime.data.source.remote.response.trailer.TrailerItem
import com.amora.myseasonalanime.views.features.detail.characters.CharactersAdapter
import com.amora.myseasonalanime.views.features.detail.trailer.TrailerAdapters
import com.amora.myseasonalanime.views.features.home.HomeAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.BlurTransformation

/* HomeFragment Adapter RecyclerView
* */
@BindingAdapter("listDataHome")
fun homeBindRecyclerView(recyclerView: RecyclerView, data: List<AnimeListResponse?>?) {
    val homeAdapter = recyclerView.adapter as HomeAdapter
    homeAdapter.submitList(data)
}

/* CharactersResponse Adapter in DetailFragment that has recyclerview
* */
@BindingAdapter("listDataChara")
fun charaBindRecyclerView(recyclerView: RecyclerView, data: List<CharaItem?>?) {
    val charaAdapter = recyclerView.adapter as CharactersAdapter
    charaAdapter.submitList(data)
}

@BindingAdapter("listTrailerAnime")
fun trailerBindRecyclerView(recyclerView: RecyclerView, data: List<TrailerItem?>?) {
    val charaAdapter = recyclerView.adapter as TrailerAdapters
    charaAdapter.submitList(data)
}

/* Image Url
* */
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    Glide.with(imgView.context).load(imgUrl)
        .centerCrop()
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(imgView)
}

/* Blurring Image
* */
@BindingAdapter("showBlurImage")
fun blurImage(imgView: ImageView, imgUrl: String?) {
    Glide.with(imgView.context).load(imgUrl)
        .apply(RequestOptions.bitmapTransform(BlurTransformation(25, 3))).into(imgView)
}