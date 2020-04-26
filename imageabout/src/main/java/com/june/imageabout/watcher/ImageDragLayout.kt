package com.june.imageabout.watcher

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout

class ImageDragLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var mImageDragListener: OnImageDragListener? = null

    override fun onTouchEvent(event: MotionEvent): Boolean {
        super.onTouchEvent(event)
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                mImageDragListener?.onDragStateChange(MotionEvent.ACTION_DOWN, event.x, event.y)
            }
            MotionEvent.ACTION_MOVE -> {
                mImageDragListener?.onDragStateChange(MotionEvent.ACTION_MOVE, event.x, event.y)
                translationY = event.y
            }
            MotionEvent.ACTION_UP -> {
                mImageDragListener?.onDragStateChange(MotionEvent.ACTION_UP, event.x, event.y)
            }
            MotionEvent.ACTION_CANCEL -> {
                mImageDragListener?.onDragStateChange(MotionEvent.ACTION_CANCEL, event.x, event.y)
            }
        }
        return true
    }

    fun setImageDragListener(listener: OnImageDragListener) {
        mImageDragListener = listener
    }
}