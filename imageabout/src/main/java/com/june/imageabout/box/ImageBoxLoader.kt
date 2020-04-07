package com.june.imageabout.box

import androidx.appcompat.widget.AppCompatImageView

/**
 * imageBox 图片加载器
 */
interface ImageBoxLoader {

    //加载图片
    fun loadImage(imageView: AppCompatImageView, image: ImageVo, position: Int)

    //点击事件
    fun clickImage(imageView: AppCompatImageView, imageList: MutableList<ImageVo>, position: Int)
}