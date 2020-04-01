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
    private var mPath = Path()
    private var mRectF = RectF()

    private var radiusArray: FloatArray? = null

    init {
        radiusArray = floatArrayOf(
            mRadius, mRadius,
            mRadius, mRadius,
            mRadius, mRadius,
            mRadius, mRadius
        )
    }

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
        //Path.Direction.CW	clockwise ，沿顺时针方向绘制
        //Path.Direction.CCW	counter-clockwise ，沿逆时针方向绘制
        radiusArray?.let {
            if (mRectF.right == 0F || mRectF.bottom == 0F) {
                mRectF.left = 0f
                mRectF.right = width.toFloat()
                mRectF.top = 0f
                mRectF.bottom = height.toFloat()
            }
            //如果不reset() Path会出异常(具体表现：圆角Clip局部无效)
            mPath.reset()
            mPath.addRoundRect(mRectF, it, Path.Direction.CCW)
        }

        canvas.clipPath(mPath)  //设置可显示的区域，canvas四个角会被剪裁掉
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

    fun resetMaxOver() {
        mOverCount = 0
        isMaxOver = false
    }

    fun setMaxOver(overCount: Int, enable: Boolean) {
        isMaxOver = enable
        mOverCount = overCount
    }

    fun setCorner(radius: Float, cornerType: Int = CORNER_ALL) {
        when (cornerType) {
            CORNER_LEFT -> {
                radiusArray?.forEachIndexed { index, _ ->
                    if (index <= 1 || index >= 6) {
                        radiusArray?.set(index, radius)
                    } else {
                        radiusArray?.set(index, 0F)
                    }
                }
            }
            CORNER_TOP -> {
                radiusArray?.forEachIndexed { index, _ ->
                    if (index <= 3) {
                        radiusArray?.set(index, radius)
                    } else {
                        radiusArray?.set(index, 0F)
                    }
                }
            }
            CORNER_RIGHT -> {
                radiusArray?.forEachIndexed { index, _ ->
                    if (index in 2..5) {
                        radiusArray?.set(index, radius)
                    } else {
                        radiusArray?.set(index, 0F)
                    }
                }
            }
            CORNER_BOTTOM -> {
                radiusArray?.forEachIndexed { index, _ ->
                    if (index in 4..7) {
                        radiusArray?.set(index, radius)
                    } else {
                        radiusArray?.set(index, 0F)
                    }
                }
            }
            CORNER_DIAGONAL_LEFT -> {
                radiusArray?.forEachIndexed { index, _ ->
                    if (index <= 1 || index == 4 || index == 5) {
                        radiusArray?.set(index, radius)
                    } else {
                        radiusArray?.set(index, 0F)
                    }
                }
            }
            CORNER_DIAGONAL_RIGHT -> {
                radiusArray?.forEachIndexed { index, _ ->
                    if (index == 2 || index == 3 || index == 6 || index == 7) {
                        radiusArray?.set(index, radius)
                    } else {
                        radiusArray?.set(index, 0F)
                    }
                }
            }
            CORNER_ONLY_LEFT_TOP -> {
                radiusArray?.forEachIndexed { index, _ ->
                    if (index <= 1) {
                        radiusArray?.set(index, radius)
                    } else {
                        radiusArray?.set(index, 0F)
                    }
                }
            }
            CORNER_ONLY_RIGHT_TOP -> {
                radiusArray?.forEachIndexed { index, _ ->
                    if (index == 2 || index == 3) {
                        radiusArray?.set(index, radius)
                    } else {
                        radiusArray?.set(index, 0F)
                    }
                }
            }
            CORNER_ONLY_LEFT_BOTTOM -> {
                radiusArray?.forEachIndexed { index, _ ->
                    if (index == 6 || index == 7) {
                        radiusArray?.set(index, radius)
                    } else {
                        radiusArray?.set(index, 0F)
                    }
                }
            }
            CORNER_ONLY_RIGHT_BOTTOM -> {
                radiusArray?.forEachIndexed { index, _ ->
                    if (index == 4 || index == 5) {
                        radiusArray?.set(index, radius)
                    } else {
                        radiusArray?.set(index, 0F)
                    }
                }
            }
            else -> {
                radiusArray?.forEachIndexed { index, _ ->
                    radiusArray?.set(index, radius)
                }
            }
        }
        postInvalidate()
    }

    companion object {
        const val CORNER_ALL = 0

        const val CORNER_LEFT = 1
        const val CORNER_TOP = 2
        const val CORNER_RIGHT = 3
        const val CORNER_BOTTOM = 4

        const val CORNER_DIAGONAL_LEFT = 5
        const val CORNER_DIAGONAL_RIGHT = 6

        const val CORNER_ONLY_LEFT_TOP = 7
        const val CORNER_ONLY_RIGHT_TOP = 8
        const val CORNER_ONLY_LEFT_BOTTOM = 9
        const val CORNER_ONLY_RIGHT_BOTTOM = 10
    }
}