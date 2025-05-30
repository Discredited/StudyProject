package com.june.studyproject.base.ext

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.june.style.R
import timber.log.Timber

/////////////////////测试
fun ImageView.loadCircleImage(
    url: String
) {
    Glide.with(context)
        .load(url)
        .transform(CenterCrop(), CircleCrop())
        .into(this)
}


fun ImageView.loadRoundImage(
    url: String,
    radius: Int = resources.getDimensionPixelSize(R.dimen.dp_10)
) {
    Glide.with(context)
        .load(url)
        .transform(CenterCrop(), RoundedCorners(radius))
        .into(this)
}

fun ImageView.loadImageBoxItem(url: String, width: Int, height: Int) {

    Timber.i("width:$width    height:$height")

    Glide.with(context)
        .load(url)
        .transform(CenterCrop())
        .into(this)
}

fun ImageView.loadImage(url: String) {
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.layer_placeholder_square)
        .error(R.drawable.layer_placeholder_square)
        .into(this)
}