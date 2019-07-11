package com.voronin.shakuro.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

/**
 * TODO
 */
object BindingAdapter {

    @BindingAdapter("app:url")
    fun loadImage(view: ImageView, url: String) {
        Picasso.get().load(url).into(view)
    }
}