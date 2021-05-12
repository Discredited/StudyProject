package com.june.widget.loading

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.View
import kotlin.math.abs
import kotlin.math.cos

class JumpLoadingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    // loading文本
    private val mText = "New Loading..."

    // 文本跳跃的高度
    //private val mJumpHeight = 50

    private val mGap = 10

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val mWidthList = mutableListOf<Int>()
    private val mHeightList = mutableListOf<Int>()

    private val mRect = Rect()

    private var threshold: Int = 0
        set(value) {
            field = value
            invalidate()
        }

    init {
        mPaint.textSize = 100F

        // 处理并获取文本的实际高度
        measureTextHeight()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val measureWidth = MeasureSpec.getSize(widthMeasureSpec)
        val measureHeight = MeasureSpec.getSize(heightMeasureSpec)

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        val textWidth = mPaint.measureText(mText).toInt() + (mGap shl 2)
        val textHeight = (mPaint.descent() - mPaint.ascent()).toInt() shl 2

        val requestWidth = when (widthMode) {
            MeasureSpec.EXACTLY -> if (textWidth > measureWidth) textWidth else measureWidth
            MeasureSpec.AT_MOST -> textWidth
            else -> measureWidth.coerceAtLeast(textWidth)
        }

        val requestHeight = when (heightMode) {
            MeasureSpec.EXACTLY -> if (textHeight > measureHeight) textHeight else measureHeight
            MeasureSpec.AT_MOST -> textHeight
            else -> measureHeight.coerceAtLeast(textHeight)
        }

        setMeasuredDimension(
            requestWidth,
            requestHeight
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val textXBegin = (width - mPaint.measureText(mText)) / 2F  // 文字绘制的起始X位置
        val textYBottom = height - mPaint.descent() + mPaint.ascent()  // 文字绘制左下角起始位置

        var textX = textXBegin
        for (index in mText.indices) {
            val textY = obtainTextY(index, threshold, textYBottom)
            //Log.i("June", "${mText[index]}  x:${textX}  width:${mWidthList[index]}  y:${textYTop}  height:${mHeightList[index]}")
            canvas.drawText(mText, index, index + 1, textX, textY, mPaint)

            textX += if (mWidthList[index] > 0) (mWidthList[index] + mGap).toFloat() else (mGap shl 2).toFloat()
        }
    }

    private fun measureTextHeight() {
        mHeightList.clear()
        for (index in mText.indices) {
            mPaint.getTextBounds(mText, index, index + 1, mRect)
            Log.w("June", "${mText[index]}  width:${mRect.right - mRect.left}  height:${mRect.bottom - mRect.top}")
            mWidthList.add(index, mRect.right - mRect.left)
            mHeightList.add(index, mRect.bottom - mRect.top)
        }
    }

    private fun obtainTextY(index: Int, threshold: Int, textYBottom: Float): Float {
        return if (index >= threshold - 1 && index <= threshold + 1) {
            val cosHeight = abs(mHeightList[index] * cos(index * 15F % 90F))
            textYBottom - cosHeight
        } else {
            textYBottom
        }
    }

    private var mJumpAnimator: ObjectAnimator? = null
    fun beginAnimator() {
        mJumpAnimator = ObjectAnimator.ofInt(
            this,
            "threshold",
            0,
            mText.length
        )
        mJumpAnimator?.duration = 2000
        mJumpAnimator?.repeatCount = -1
        mJumpAnimator?.start()
    }

    fun endAnimator() {
        mJumpAnimator?.end()
    }
}