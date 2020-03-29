package com.june.studyproject.base.ext

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.june.studyproject.R
import com.june.studyproject.common.ConstHelper

/////////////////////测试
fun ImageView.loadRoundImage(position: Int) {
    val url = ConstHelper.IMAGE_SOURCE[(position % (ConstHelper.IMAGE_SOURCE.size - 1))]
    val radius: Int = resources.getDimensionPixelSize(R.dimen.dp_5)
    Glide.with(context)
        .load(url)
        .transform(CenterCrop(), RoundedCorners(radius))
        .into(this)
}

fun ImageView.loadRoundImage(
    url: String,
    radius: Int = resources.getDimensionPixelSize(R.dimen.dp_5)
) {
    Glide.with(context)
        .load(url)
        .transform(CenterCrop(), RoundedCorners(radius))
        .into(this)
}