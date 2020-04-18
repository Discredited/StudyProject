package com.june.studyproject.expand.image.box

import android.widget.ImageView
import com.blankj.utilcode.util.ToastUtils
import com.june.imageabout.box.ImageBoxLoader
import com.june.imageabout.vo.ImageVo
import com.june.imageabout.watcher.ImageWatchActivity
import com.june.studyproject.base.ext.loadImageBoxItem

class ImageLoader : ImageBoxLoader<ImageVo> {

    override fun loadImage(imageView: ImageView,
                           image: ImageVo,
                           position: Int,
                           width: Int,
                           height: Int) {
        imageView.loadImageBoxItem(image.url, width, height)
    }

    override fun clickImage(imageView: ImageView, imageList: MutableList<ImageVo>, position: Int) {
        ToastUtils.showShort("点击了第${position + 1} 张图片")
        ImageWatchActivity.starter(imageView.context, imageList, position)
    }

    override fun singleImageWidth(image: ImageVo, singleType: Int): Int {
        return image.width
    }

    override fun singleImageHeight(image: ImageVo, singleType: Int): Int {
        return image.height
    }
}