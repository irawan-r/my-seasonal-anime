package com.amora.myseasonalanime.views.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amora.myseasonalanime.data.source.remote.response.characters.CharaItem
import com.amora.myseasonalanime.data.source.remote.response.detail.DetailItem
import com.amora.myseasonalanime.data.source.remote.response.detail.GenresItem
import com.amora.myseasonalanime.views.features.home.HomeAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.BlurTransformation

/* HomeFragment Adapter RecyclerView
* */
@BindingAdapter("listDataHome")
fun homeBindRecyclerView(recyclerView: RecyclerView, data: List<DetailItem>?) {
    val homeAdapter = recyclerView.adapter as HomeAdapter
    homeAdapter.submitList(data)
}

/* Characters Adapter in DetailFragment that has recyclerview
* */
@BindingAdapter("listDataChara")
fun charaBindRecyclerView(recyclerView: RecyclerView, data: List<CharaItem>) {
    val charaAdapter = recyclerView.adapter as CharactersAdapter
    charaAdapter.submitList(data)
}

/* Genre Adapter in DetailFragment that has recyclerview
* */
@BindingAdapter("listGenreAnime")
fun genresBindRecyclerView(recyclerView: RecyclerView, data: List<GenresItem>) {
    val genresAdapter = recyclerView.adapter as GenresAdapter
    genresAdapter.submitList(data)
}

/* Image Url
* */
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    Glide.with(imgView.context).load(imgUrl).into(imgView)
}

/* Blurring Image
* */
@BindingAdapter("showBlurImage")
fun blurImage(imgView: ImageView, imgUrl: String?) {
    Glide.with(imgView.context).load(imgUrl)
        .apply(RequestOptions.bitmapTransform(BlurTransformation(25, 3))).into(imgView)
}