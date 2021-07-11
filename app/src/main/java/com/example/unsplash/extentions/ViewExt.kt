package com.example.unsplash.extentions

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.unsplash.data.model.Image
import java.lang.Byte.SIZE
import java.lang.Character.SIZE

fun View.toVisible() {
    this.visibility = View.VISIBLE
}

fun View.toGone() {
    this.visibility = View.GONE
}

fun ImageView.loadFromUrl(url: String) {
    Glide.with(this.context.applicationContext)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .skipMemoryCache(false)
        .into(this)
}
