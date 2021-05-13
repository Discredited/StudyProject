package com.june.widget.loading

import android.animation.ObjectAnimator
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

    private val mGap = 10

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val mWidthList = mutableListOf<Int>()
    private val mHeightList = mutableListOf<Int>()

    private val mRect = Rect()

    // 对称轴初始值
    private val axisInitValue = -2F

    // 函数对称轴的位置，用于函数右平移的动画数值变化
    private var mAxis: Float = axisInitValue
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
            val textY = obtainTextY(index, textYBottom)
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

    /**
     * 每个字符Y轴坐标的获取
     *
     * 该效果可以看作是y = (-4x^2 + 1)函数从 -0.5 平移 到 mText.length + 0.5 的效果
     * 二次方程的平移公式：上+，下-，左+，右-
     * 即 y = 4(x - mAxis)^2
     * index对应方程中的x
     */
    private fun obtainTextY(index: Int, textYBottom: Float): Float {
        val percent = -0.5F * (index - mAxis) * (index - mAxis) + 1F
        val sinHeight = if (percent in 0F..1F) {
            mHeightList[index] * percent
        } else {
            0F
        }

        Log.i("June", "index:${index}  ${mText[index]}  threshold:${mAxis}  percent:${-0.5F * (index - mAxis) * (index - mAxis) + 1F}  sinHeight:${sinHeight}")

        return textYBottom - sinHeight
    }

    private var mJumpAnimator: ObjectAnimator? = null
    fun beginAnimator() {
        if (null == mJumpAnimator) {
            mJumpAnimator = ObjectAnimator.ofFloat(
                this,
                "mAxis",
                axisInitValue,
                // axisInitValue是个负数
                mText.length - axisInitValue
            )
        }
        mJumpAnimator?.duration = 2000
        mJumpAnimator?.repeatCount = -1
        mJumpAnimator?.start()
    }

    fun endAnimator() {
        mJumpAnimator?.end()
    }
}