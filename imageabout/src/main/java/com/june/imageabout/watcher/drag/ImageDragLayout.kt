package com.june.imageabout.watcher.drag

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import timber.log.Timber
import kotlin.math.abs

class ImageDragLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var mDownRawY = 0.0F  //手指按下在屏幕的位置
    private var mTranslationY = 0.0F  //Y轴偏移的位置

    private var mOverThreshold = 0.0F

    private var mImageDragListener: OnImageDragListener? = null
    private var mPhotoView: View? = null

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val measureHeightHalf = MeasureSpec.getSize(heightMeasureSpec) shr 2
        mOverThreshold = measureHeightHalf.toFloat()
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        // true 拦截事件并交给自身的onTouchEvent处理
        // false or super 不拦截事件，传递给子视图
        val intercept = when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                mDownRawY = event.rawY
                false
            }
            MotionEvent.ACTION_MOVE -> {
                abs(event.rawY - mDownRawY) > 200
            }
            else -> false
        }
        requestDisallowInterceptTouchEvent(intercept)
        return intercept
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                mDownRawY = event.rawY
                mImageDragListener?.onDragStateChange(MotionEvent.ACTION_DOWN, event.x, event.y)
            }
            MotionEvent.ACTION_UP -> {
                mImageDragListener?.onDragStateChange(MotionEvent.ACTION_UP, event.x, event.y)
                Timber.e("translationY:$mTranslationY    OverThreshold:$mOverThreshold")
                if (abs(mTranslationY) > mOverThreshold) {
                    mImageDragListener?.onDragOverThreshold()
                } else {
                    mTranslationY = 0F
                    translationY = 0F
                    scaleX = 1F
                    scaleY = 1F
                    mPhotoView?.setBackgroundColor(Color.argb(255, 0, 0, 0))
                }
            }
            MotionEvent.ACTION_MOVE -> {
                mImageDragListener?.onDragStateChange(MotionEvent.ACTION_MOVE, event.x, event.y)
                mTranslationY = event.rawY - mDownRawY
                translationY = mTranslationY

                //缩放设置
                val percent = (height - abs(mTranslationY)) / height
                scaleX = percent
                scaleY = percent
                mPhotoView?.setBackgroundColor(Color.argb(0, 0, 0, 0))
            }
            MotionEvent.ACTION_CANCEL -> {
                mImageDragListener?.onDragStateChange(MotionEvent.ACTION_CANCEL, event.x, event.y)
                mTranslationY = 0F
                translationY = 0F
                scaleX = 1F
                scaleY = 1F
                mPhotoView?.setBackgroundColor(Color.argb(255, 0, 0, 0))
            }
        }
        return true
    }

    fun setImageDragListener(listener: OnImageDragListener) {
        mImageDragListener = listener
    }

    fun bindView(view: View) {
        mPhotoView = view
    }
}