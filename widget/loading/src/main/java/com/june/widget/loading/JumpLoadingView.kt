package com.june.widget.loading

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.View

class JumpLoadingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    // loading文本
    private val mText = "New Loading..."

    // 文本跳跃的高度
    //private val mJumpHeight = 50

    private val mGap = 20

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val mHeightList = mutableListOf<Int>()

    private val mRect = Rect()

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
        val textX = (width - mPaint.measureText(mText)) / 2F  // 文字绘制的起始X位置
        val textY = height - mPaint.descent() + mPaint.ascent()  // 文字绘制左下角起始位置

        // 第一个文字高一点
        canvas.drawText(mText, 0, 1, textX, textY - mHeightList[0], mPaint)
        canvas.drawText(mText, 1, mText.length - 1, textX + mHeightList[0], textY, mPaint)
    }

    private fun measureTextHeight() {
        mHeightList.clear()
        for (index in mText.indices) {
            mPaint.getTextBounds(mText, index, index + 1, mRect)
            Log.w("June", "${mText[index]}  width:${mRect.right - mRect.left}  height:${mRect.bottom - mRect.top}")
            mHeightList.add(index, mRect.bottom - mRect.top)
        }
    }
}