package com.june.imageabout.watcher

class ImageWatcherHelper private constructor() {

    companion object {
        val instance: ImageWatcherHelper by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ImageWatcherHelper()
        }
    }
}