package com.june.imageabout.box

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatImageView
import com.june.imageabout.R
import timber.log.Timber

class BoxImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private val mColorFilter = PorterDuffColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY)

    private var isMaxOver = false
    private var mOverCount = 0  //超出数量

    private var mRadius = resources.getDimension(R.dimen.box_image_default_radius)
    private var mOverTextSize = resources.getDimension(R.dimen.box_image_default_over_text_size)
    private var mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onTouchEvent(event: MotionEvent): Boolean {
        super.onTouchEvent(event)
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                Timber.e("ACTION_DOWN  X:${event.x}  Y:${event.y}")
                drawable?.mutate()?.colorFilter = mColorFilter
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                Timber.e("ACTION_MOVE  X:${event.x}  Y:${event.y}")
            }
            MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
                drawable?.mutate()?.clearColorFilter()
                Timber.e("ACTION_UP  X:${event.x}  Y:${event.y}")
            }
        }
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val needDrawOver = mOverCount > 0
        if (isMaxOver && needDrawOver) {
            //绘制背景
            val left = 0F
            val right = width.toFloat()
            val top = 0F
            val bottom = height.toFloat()
            val radius = mRadius
            mPaint.color = Color.parseColor("#33000000")
            canvas.drawRoundRect(left, top, right, bottom, radius, radius, mPaint)
            //绘制文字
            mPaint.color = Color.parseColor("#FFFFFF")
            mPaint.textSize = mOverTextSize
            mPaint.ascent()
            val drawString = "+$mOverCount"
            val stringWidth = mPaint.measureText(drawString)
            val stringHeight = mPaint.ascent()
            val textX = (width - stringWidth) / 2
            val textY = (height - stringHeight) / 2
            canvas.drawText(drawString, textX, textY, mPaint)
        }
    }

    fun setMaxOver(overCount: Int, enable: Boolean) {
        isMaxOver = enable
        mOverCount = overCount
    }

    fun resetMaxOver() {
        mOverCount = 0
        isMaxOver = false
    }
}