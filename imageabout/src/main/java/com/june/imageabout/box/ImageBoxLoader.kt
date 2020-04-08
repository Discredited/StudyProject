package com.june.imageabout.box

import android.widget.ImageView
import com.june.imageabout.vo.ImageVo

/**
 * imageBox 图片加载器
 */
interface ImageBoxLoader {

    //加载图片
    fun loadImage(imageView: ImageView, image: ImageVo, position: Int)

    //点击事件
    fun clickImage(imageView: ImageView, imageList: MutableList<ImageVo>, position: Int)
}