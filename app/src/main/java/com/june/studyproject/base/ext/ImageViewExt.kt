package com.june.studyproject.base.ext

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.june.studyproject.R

fun ImageView.loadRoundImage(
    url: String,
    radius: Int = resources.getDimensionPixelSize(R.dimen.dp_5)
) {
    Glide.with(context)
        .load(url)
        .transform(CenterCrop(), RoundedCorners(radius))
        .into(this)
}