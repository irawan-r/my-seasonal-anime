package com.amora.myseasonalanime.views.adapter

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.provider.MediaStore.Images.Media.getBitmap
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amora.myseasonalanime.data.source.remote.response.characters.CharaItems
import com.amora.myseasonalanime.data.source.remote.response.trailer.TrailerItem
import com.amora.myseasonalanime.data.source.remote.response.voiceactor.VoiceActors
import com.amora.myseasonalanime.views.features.detail.characters.CharactersAdapter
import com.amora.myseasonalanime.views.features.detail.characters.detail.VoiceActorAdapter
import com.amora.myseasonalanime.views.features.detail.trailer.TrailerAdapter
import com.amora.myseasonalanime.views.features.home.HomeAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import jp.wasabeef.glide.transformations.BlurTransformation

/* HomeFragment Adapter RecyclerView
* */
@BindingAdapter("listDataHome")
fun homeBindRecyclerView(
    recyclerView: RecyclerView,
    data: List<com.amora.myseasonalanime.data.source.remote.response.anime.Anime?>?,
) {
    val homeAdapter = recyclerView.adapter as HomeAdapter
    homeAdapter.submitList(data)
}

/* CharactersResponse Adapter in DetailFragment's recyclerview
* */
@BindingAdapter("listDataChara")
fun charaBindRecyclerView(recyclerView: RecyclerView, data: List<CharaItems?>?) {
    val charaAdapter = recyclerView.adapter as CharactersAdapter
    charaAdapter.submitList(data)
}

/* TrailerResponse Adapter in DetailFragment's recyclerview
* */
@BindingAdapter("listTrailerAnime")
fun trailerBindRecyclerView(recyclerView: RecyclerView, data: List<TrailerItem?>?) {
    val charaAdapter = recyclerView.adapter as TrailerAdapter
    charaAdapter.submitList(data)
}

/* DetailChara Adapter in DialogAlert
* */
@BindingAdapter("listVoiceActor")
fun detailCharaBindRecyclerView(recyclerView: RecyclerView, data: List<VoiceActors?>?) {
    val detailCharaAdapter = recyclerView.adapter as VoiceActorAdapter
    detailCharaAdapter.submitList(data)
}

/* Image Url
* */
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    Glide.with(imgView.context)
        .asBitmap()
        .load(imgUrl)
        .centerCrop()
        .into(object : CustomTarget<Bitmap>(){
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                imgView.setImageBitmap(resource)
                Log.d("TAG", "Image ready")
            }
            override fun onLoadCleared(placeholder: Drawable?) {
                // this is called when imageView is cleared on lifecycle call or for
                // some other reason.
                // if you are referencing the bitmap somewhere else too other than this imageView
                // clear it here as you can no longer have the bitmap
                Log.d("TAG", "onCleared: Image was")
            }
        })
}

/* Blurring Image
* */
@BindingAdapter("showBlurImage")
fun blurImage(imgView: ImageView, imgUrl: String?) {
    Glide.with(imgView.context).load(imgUrl)
        .apply(RequestOptions.bitmapTransform(BlurTransformation(25, 3))).into(imgView)
}