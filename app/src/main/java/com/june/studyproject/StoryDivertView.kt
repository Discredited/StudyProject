package com.june.studyproject

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View


/**
 * 故事书引流遮罩
 *
 * @author June
 * @date 2024/5/22
 */
class StoryDivertView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var mThemeColor = Color.parseColor("#025087")
    private val mRect = Rect()

    private val cornerRadius = 40F

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mPath = Path()

    // https://developer.android.com/reference/android/graphics/PorterDuff.Mode
    // 注意这里的混合模式，使用的是 DST_IN
    // 保留覆盖源像素的目标像素，丢弃剩余的源像素和目标像素（正交显示目标层，且原像素交集处像素保留）
    private val xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)

    private var maskBitmap: Bitmap? = null

    init {
        // 如果想要混合模式显示正常，必须要设置LayerType
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        maskBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.img_story_mask)
    }

    override fun dispatchDraw(canvas: Canvas) {
        mPath.reset()
        mPath.addRoundRect(
            0f,
            0f,
            width.toFloat(),
            height.toFloat(),
            cornerRadius,
            cornerRadius,
            Path.Direction.CW
        )
        canvas.clipPath(mPath)
        super.dispatchDraw(canvas)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (mThemeColor == 0) return

        mRect.left = 0
        mRect.right = width
        mRect.top = 0
        mRect.bottom = height

        // 主题颜色绘制在底部，即Source层
        mPaint.color = mThemeColor
        canvas.drawRoundRect(0F, 0F, width.toFloat(), height.toFloat(), cornerRadius, cornerRadius, mPaint)

        // 给Paint设置好混合模式
        mPaint.xfermode = xfermode

        // 使用混合模式绘制Bitmap,即Destination层
        maskBitmap?.let {
            canvas.drawBitmap(it, null, mRect, mPaint)
        }

        mPaint.xfermode = null
    }

    fun setThemeColor(themeColor: Int) {
        mThemeColor = themeColor
        invalidate()
    }
}