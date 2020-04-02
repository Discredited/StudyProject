package com.june.studyproject.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.june.studyproject.R

class TitleBarLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : Toolbar(context, attrs, defStyleAttr) {

    private var mDrawDivider: Boolean = true
    private var mDividerHeight: Int = resources.getDimensionPixelSize(R.dimen.dp_1)
    private var mDividerColor: Int = ContextCompat.getColor(context, R.color.color_red)
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        mPaint.color = mDividerColor
        mPaint.style = Paint.Style.FILL
        if (mDrawDivider) {
            setWillNotDraw(false)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (mDrawDivider) {
            val startY = (height - mDividerHeight).toFloat()
            canvas.drawLine(0F, startY, width.toFloat(), height.toFloat(), mPaint)
        }
    }
}