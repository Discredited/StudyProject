package com.june.studyproject.expand.img

import androidx.appcompat.widget.AppCompatImageView
import com.june.imageabout.box.ImageBoxLoader
import com.june.imageabout.box.ImageVo
import com.june.studyproject.base.ext.loadRoundImage

class ImageLoader:ImageBoxLoader{
    override fun loadImage(imageView: AppCompatImageView, image: ImageVo, position: Int) {
        imageView.loadRoundImage(position)
    }
}