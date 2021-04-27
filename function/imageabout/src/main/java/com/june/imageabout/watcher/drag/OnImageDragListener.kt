package com.june.imageabout.watcher.drag

interface OnImageDragListener {

    fun onDragStateChange(state: Int, x: Float, y: Float)

    fun onDragOverThreshold()
}