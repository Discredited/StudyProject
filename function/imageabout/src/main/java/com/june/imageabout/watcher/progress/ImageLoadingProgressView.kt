package com.june.imageabout.watcher.progress

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.june.studyproject.imageabout.R

class ImageLoadingProgressView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    //外环形线宽
    private var mStrokeWidth: Float = 10F

    //外环与扇形的间隙
    private var mGap: Float = 10F

    //环形的半径
    private var mCircleRadius: Float = 0F

    //扇形的位置
    private val mSectorRectF: RectF = RectF()

    //进度颜色
    private var mProgressColor = Color.YELLOW

    //文字颜色
    private var mTextColor = Color.WHITE
    private var mTextSize = 0F

    //当前进度
    private var mProgress: Float = 0F

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        val array = context.obtainStyledAttributes(attrs, R.styleable.ImageLoadingProgressView, defStyleAttr, 0)
        try {
            mStrokeWidth = array.getDimension(
                R.styleable.ImageLoadingProgressView_loading_stroke_width,
                resources.getDimension(R.dimen.image_loading_stroke_width)
            )
            mGap = array.getDimension(
                R.styleable.ImageLoadingProgressView_loading_stroke_gap,
                resources.getDimension(R.dimen.image_loading_stroke_gap)
            )
            mProgressColor = array.getColor(
                R.styleable.ImageLoadingProgressView_loading_progress_color,
                Color.YELLOW
            )
            mTextColor = array.getColor(
                R.styleable.ImageLoadingProgressView_loading_text_color,
                Color.WHITE
            )
            mTextSize = array.getDimension(
                R.styleable.ImageLoadingProgressView_loading_text_size,
                resources.getDimension(R.dimen.image_loading_text_size)
            )
            mProgress = array.getFloat(
                R.styleable.ImageLoadingProgressView_loading_progress,
                0F
            )
        } finally {
            array.recycle()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val recommendWidth = MeasureSpec.getSize(widthMeasureSpec)
        val recommendHeight = MeasureSpec.getSize(heightMeasureSpec)
        val size = recommendWidth.coerceAtMost(recommendHeight)
        mCircleRadius = (size - mStrokeWidth) / 2F
        val totalGap = mStrokeWidth * 1.5F + mGap
        mSectorRectF.left = totalGap + paddingStart
        mSectorRectF.top = totalGap + paddingTop
        mSectorRectF.right = size - totalGap - paddingEnd
        mSectorRectF.bottom = size - totalGap - paddingBottom
        setMeasuredDimension(size, size)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val pointX = (width shr 1).toFloat()
        val pointY = (height shr 1).toFloat()
        //绘制边线
        mPaint.color = mProgressColor
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = mStrokeWidth
        canvas.drawCircle(pointX, pointY, mCircleRadius, mPaint)

        //绘制扇形
        mPaint.style = Paint.Style.FILL
        val sweep = mProgress * 360F
        canvas.drawArc(mSectorRectF, 0F, sweep, true, mPaint)

        //绘制进度
        mPaint.color = mTextColor
        mPaint.textSize = mTextSize
        val progressString = mProgress.toString()
        val progressWidth = mPaint.measureText(progressString)
        val progressX = pointX - (progressWidth / 2F)
        val progressY = (height - (mPaint.descent() + mPaint.ascent())) / 2F
        canvas.drawText(progressString, progressX, progressY, mPaint)
    }

    fun setProgress(progress: Float) {
        mProgress = progress
        invalidate()
    }
}