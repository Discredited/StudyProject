package com.june.studyproject.expand.image.box

import androidx.appcompat.widget.AppCompatImageView
import com.blankj.utilcode.util.ToastUtils
import com.june.imageabout.box.ImageBoxLoader
import com.june.imageabout.box.ImageVo
import com.june.studyproject.base.ext.loadImageBoxItem

class ImageLoader : ImageBoxLoader {

    override fun loadImage(imageView: AppCompatImageView, image: ImageVo, position: Int) {
        imageView.loadImageBoxItem(image.url)
    }

    override fun clickImage(imageView: AppCompatImageView, imageList: MutableList<ImageVo>, position: Int) {
        ToastUtils.showShort("点击了第${position + 1} 张图片")
    }
}