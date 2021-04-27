package com.june.imageabout.watcher.progress

interface LoadingProgressListener {

    fun onProgress(progress: Long, total: Long, percent: Float)
}