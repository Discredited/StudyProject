package com.june.widget.loading

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import kotlin.math.abs

class JumpLoadingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    // loading文本
    private var mText = "New Loading..."

    // 字符间隔
    private var mGap = 10

    // 方程的系数，影响一次有多少个字符参与跳动，系数越小，一次跳动的字符越多
    // 推荐值(0.25 < mCoefficient < 2)
    private var mCoefficient = 0.5F

    // 对称轴初始值，方程的系数有关
    private var axisInitValue = -2F

    // 函数对称轴的位置，用于函数右平移的动画数值变化
    private var mAxis: Float = axisInitValue
        set(value) {
            field = value
            invalidate()
        }

    // 一次跳动完成需要的事件
    private var mJumpDuration: Long = 2000L

    // 字符宽度集合
    private val mWidthList = mutableListOf<Int>()

    // 字符高度集合
    private val mHeightList = mutableListOf<Int>()

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mRect = Rect()

    init {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.JumpLoadingView, defStyleAttr, 0)
        try {
            mPaint.textSize = typeArray.getDimension(R.styleable.JumpLoadingView_jumpTextSize, 100F)
            mText = typeArray.getString(R.styleable.JumpLoadingView_jumpText) ?: "New Loading..."

            mGap = typeArray.getDimensionPixelSize(R.styleable.JumpLoadingView_jumpTextGap, 10)
            mCoefficient = typeArray.getFloat(R.styleable.JumpLoadingView_jumpCoefficient, 0.5F)

            mJumpDuration = typeArray.getInt(R.styleable.JumpLoadingView_jumpDuration, 2000).toLong()
        } finally {
            typeArray.recycle()
        }

        axisInitValue = -1 / mCoefficient
        mAxis = axisInitValue

        // 处理并获取文本的实际高度
        measureTextHeight()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val measureWidth = MeasureSpec.getSize(widthMeasureSpec)
        val measureHeight = MeasureSpec.getSize(heightMeasureSpec)

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        val textWidth = mPaint.measureText(mText).toInt() + (mGap shl 2)
        val textHeight = (abs(mPaint.descent() + mPaint.ascent()) + mGap).toInt() shl 2

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
            MeasureSpec.makeMeasureSpec(requestWidth, widthMode),
            MeasureSpec.makeMeasureSpec(requestHeight, heightMode)
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val textXBegin = (width - mPaint.measureText(mText)) / 2F  // 文字绘制的起始X位置
        val textYBottom = height + mPaint.ascent() + mPaint.descent() - mGap  // 文字绘制左下角起始位置

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
        val percent = -mCoefficient * (index - mAxis) * (index - mAxis) + 1F
        val sinHeight = if (percent in 0F..1F) {
            mHeightList[index] * percent
        } else {
            0F
        }
        return textYBottom - sinHeight
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        beginAnimator()
    }

    override fun onDetachedFromWindow() {
        endAnimator()
        super.onDetachedFromWindow()
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
        mJumpAnimator?.duration = mJumpDuration
        mJumpAnimator?.repeatCount = -1
        mJumpAnimator?.startDelay = 1000
        mJumpAnimator?.start()
    }

    fun endAnimator() {
        mJumpAnimator?.end()
    }
}