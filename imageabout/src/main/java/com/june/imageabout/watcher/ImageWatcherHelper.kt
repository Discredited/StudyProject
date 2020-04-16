package com.june.imageabout.watcher

import com.june.imageabout.box.ImageBoxLoader
import com.june.imageabout.vo.ImageVo

class ImageWatcherHelper private constructor() {

    var mImageLoader: ImageBoxLoader<ImageVo>? = null

    fun setImageLoader(loader: ImageBoxLoader<ImageVo>) {
        mImageLoader = loader
    }

    companion object {
        val instance: ImageWatcherHelper by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ImageWatcherHelper()
        }
    }
}