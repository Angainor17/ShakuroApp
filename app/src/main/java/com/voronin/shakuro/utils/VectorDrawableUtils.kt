package com.voronin.shakuro.utils

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes

fun getColoredVectorDrawable(context: Context, @DrawableRes drawableRes: Int, color: Int): Drawable {
    val drawable = context.resources.getDrawable(drawableRes)!!
    drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)

    return drawable
}
