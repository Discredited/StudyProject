package com.june.studyproject.expand.image.box

import androidx.appcompat.widget.AppCompatImageView
import com.june.imageabout.box.ImageBoxLoader
import com.june.imageabout.box.ImageVo
import com.june.studyproject.base.ext.loadImageBoxItem

class ImageLoader : ImageBoxLoader {
    override fun loadImage(imageView: AppCompatImageView, image: ImageVo, position: Int) {
        imageView.loadImageBoxItem(image.url)
    }
}