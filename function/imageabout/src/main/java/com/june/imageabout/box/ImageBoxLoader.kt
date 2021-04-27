package com.june.imageabout.box

import android.widget.ImageView

/**
 * imageBox 图片加载器
 */
interface ImageBoxLoader<T> {

    //加载图片
    fun loadImage(imageView: ImageView, image: T, position: Int, width: Int, height: Int)

    //点击事件
    fun clickImage(imageView: ImageView, imageList: MutableList<T>, position: Int)

    //单张图片的宽度
    fun singleImageWidth(image: T, singleType: Int): Int

    //单张图片的高度
    fun singleImageHeight(image: T, singleType: Int): Int
}