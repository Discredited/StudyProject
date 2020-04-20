package com.june.studyproject.expand.image.box

import android.widget.ImageView
import com.blankj.utilcode.util.ToastUtils
import com.june.imageabout.box.ImageBoxLoader
import com.june.studyproject.base.ext.loadImageBoxItem

class ImageLoader : ImageBoxLoader<MediaVo> {

    override fun loadImage(imageView: ImageView,
                           image: MediaVo,
                           position: Int,
                           width: Int,
                           height: Int) {
        imageView.loadImageBoxItem(image.url, width, height)
    }

    override fun clickImage(imageView: ImageView, imageList: MutableList<MediaVo>, position: Int) {
        ToastUtils.showShort("点击了第${position + 1} 张图片")
    }

    override fun singleImageWidth(image: MediaVo, singleType: Int): Int {
        return image.width
    }

    override fun singleImageHeight(image: MediaVo, singleType: Int): Int {
        return image.height
    }
}