package com.june.imageabout.box

import androidx.appcompat.widget.AppCompatImageView

/**
 * imageBox 图片加载器
 */
interface ImageBoxLoader {
    fun loadImage(imageView: AppCompatImageView, image: ImageVo, position: Int)
}