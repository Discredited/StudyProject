package com.june.imageabout.watcher

import com.june.imageabout.box.ImageBoxLoader

class ImageWatcherHelper private constructor() {

    var mImageLoader: ImageBoxLoader? = null

    fun setImageLoader(loader: ImageBoxLoader) {
        mImageLoader = loader
    }

    companion object {
        val instance: ImageWatcherHelper by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ImageWatcherHelper()
        }
    }
}