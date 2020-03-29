package com.june.imageabout.box

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatImageView
import com.june.imageabout.R
import timber.log.Timber

class ImageBoxLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    companion object {
        // 1 2 3
        // 4
        const val FOUR_STYLE_NORMAL = 0
        // 1 2
        // 3 4
        const val FOUR_STYLE_SQUARE = 1
    }

    private var mFourStyle: Int = 0

    private var mImageWith: Int = 0 //单张图片宽度
    private var mImageHeight: Int = 0 //单张图片高度

    private var mRow: Int = 0
    private var mColumn: Int = 0
    private var mExpectColumn: Int = 3  //默认显示三列
    private var mImageGap = 10  //图片之前的间隙

    private var mImageBoxLoader: ImageBoxLoader? = null

    private val mImageList: MutableList<ImageVo> = mutableListOf()
    private val mImageViewCache: MutableList<AppCompatImageView> = mutableListOf()

    init {
        val array = context.obtainStyledAttributes(R.styleable.ImageBoxLayout)
        try {
            mFourStyle = array.getInt(
                R.styleable.ImageBoxLayout_imageBoxFourStyle,
                FOUR_STYLE_SQUARE
            )
            mExpectColumn = array.getInt(R.styleable.ImageBoxLayout_imageBoxExpectColumn, 3)
            mImageGap = array.getDimensionPixelSize(R.styleable.ImageBoxLayout_imageBoxGap, 5)
        } finally {
            array.recycle()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height: Int = if (mImageList.size == 1) {
            mImageHeight
        } else {
            //即使size==0 也默认显示高度样式
            if (mImageList.size == 0) {
                mRow = 1 //默认显示一行
                mColumn = 1 //默认显示一列
                mImageHeight = (width - paddingLeft - paddingEnd - mImageGap * (mExpectColumn - 1)) / mExpectColumn
            }
            // 1 2
            // 3 4
            mRow * mImageHeight + mImageGap * (mRow - 1) + paddingTop + paddingBottom
        }
        setMeasuredDimension(width, height)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        //super.onLayout(changed, left, top, right, bottom)
        for (index in 0 until childCount) {
            val child = getChildAt(index)
            //child所在列
            val column = index % mColumn
            //child所在行
            val row = index / mColumn

            val childLeft = column * (mImageWith + mImageGap) + paddingLeft
            val childRight = childLeft + mImageWith
            val childTop = row * (mImageHeight + mImageGap) + paddingTop
            val childBottom = childTop + mImageHeight
            Timber.e(
                "childCount:$childCount    index:$index    childLeft:$childLeft    childRight:$childRight    childTop:$childTop    childBottom:$childBottom"
            )
            child.layout(childLeft, childTop, childRight, childBottom)
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Timber.e("onDraw")
    }

    private fun getDefaultParams(width: Int = 0, height: Int = 0): LayoutParams {
        return LayoutParams(width, height)
    }

    //从缓存中获取ImageView
    private fun getImageViewFromCache(position: Int): AppCompatImageView {
        return if (position < mImageViewCache.size) {
            //直接从缓存中读取ImageView
            val cacheImageView = mImageViewCache[position]
            if (cacheImageView.parent != null) {
                //从原父布局移除
                (cacheImageView.parent as ViewGroup).removeView(cacheImageView)
            }
            //Timber.e("position:$position    cacheImageView直接从缓存中读取  ${mImageViewCache.size}")
            cacheImageView
        } else {
            //创建新的ImageView并添加至缓存
            val newImageView = AppCompatImageView(context)
            mImageViewCache.add(newImageView)
            //Timber.e("position:$position    newImageView创建新的ImageView  ${mImageViewCache.size}")
            newImageView
        }
    }

    private fun getRowColumn(imageSize: Int, style: Int): Array<Int> {
        //row,column
        val rowColumn = arrayOf(1, 1)
        if (imageSize == 4 && style == FOUR_STYLE_SQUARE) {
            rowColumn[0] = 2
            rowColumn[1] = 2
        } else {
            //行
            rowColumn[0] = imageSize / mExpectColumn + if (imageSize % mExpectColumn == 0) {
                0
            } else {
                1
            }
            //列
            rowColumn[1] = if (imageSize >= mExpectColumn) {
                mExpectColumn
            } else {
                imageSize
            }
        }
        return rowColumn
    }

    fun setImageLoader(loader: ImageBoxLoader) {
        mImageBoxLoader = loader
    }

    fun setImageList(list: MutableList<ImageVo>) {
        if (list.isEmpty()) {
            visibility = View.GONE
        }

        Timber.e("----------------------------------------")
        Timber.e("当前ImageVo数量：${list.size}")
        removeAllViews()

        //获取行列
        val rowColumn = getRowColumn(list.size, mFourStyle)

        if (list.size == 1) {
            //单张图片
            mRow = rowColumn[0]
            mColumn = rowColumn[1]
            val vo = list[0]
            mImageWith = vo.width
            mImageHeight = vo.height

            val cacheImageView = getImageViewFromCache(0)
            mImageBoxLoader?.loadImage(cacheImageView, list[0], 0)
            addView(cacheImageView, getDefaultParams(mImageWith, mImageHeight))
        } else {
            //多张图片
            mRow = rowColumn[0]
            mColumn = rowColumn[1]

            mImageWith = (width - ((mExpectColumn - 1) * mImageGap) - paddingLeft - paddingRight) / mExpectColumn
            mImageHeight = mImageWith
            for (index in 0 until list.size) {
                val vo = list[index]
                val cacheImageView = getImageViewFromCache(index)
                mImageBoxLoader?.loadImage(cacheImageView, vo, index)
                addView(cacheImageView, getDefaultParams(mImageWith, mImageHeight))
            }
        }
        Timber.e("当前ImageWidth:$mImageWith  ImageHeight:$mImageHeight")

        mImageList.clear()
        mImageList.addAll(list)
    }
}