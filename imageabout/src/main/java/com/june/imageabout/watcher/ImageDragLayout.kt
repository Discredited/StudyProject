package com.june.imageabout.watcher

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import timber.log.Timber
import kotlin.math.abs

class ImageDragLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var mDownRawY = 0.0F  //手指按下在屏幕的位置
    private var mTranslationY = 0.0F  //Y轴偏移的位置

    private var mUpThreshold = 0.0F
    private var mDownThreshold = 0.0F

    private var mImageDragListener: OnImageDragListener? = null

    init {
        post {
            mUpThreshold = -(height / 2).toFloat()
            mDownThreshold = (height / 2).toFloat()
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        super.onTouchEvent(event)
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                mDownRawY = event.rawY
                mImageDragListener?.onDragStateChange(MotionEvent.ACTION_DOWN, event.x, event.y)
            }
            MotionEvent.ACTION_MOVE -> {
                mImageDragListener?.onDragStateChange(MotionEvent.ACTION_MOVE, event.x, event.y)
                mTranslationY = event.rawY - mDownRawY
                translationY = mTranslationY

                val percent = (height - abs(mTranslationY)) / height

                scaleX = percent
                scaleY = percent
            }
            MotionEvent.ACTION_UP -> {
                mImageDragListener?.onDragStateChange(MotionEvent.ACTION_UP, event.x, event.y)
                if (mTranslationY < mUpThreshold || mTranslationY > mDownThreshold) {
                    Timber.i("drag to close====>${mTranslationY}")
                } else {
                    mTranslationY = 0F
                    translationY = mTranslationY
                    scaleX = 1F
                    scaleY = 1F
                }
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