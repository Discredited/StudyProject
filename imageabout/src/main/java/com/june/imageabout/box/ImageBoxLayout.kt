package com.june.imageabout.box

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatImageView

class ImageBoxLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var mImageWith: Int = 0 //单张图片宽度
    private var mImageHeight: Int = 0 //单张图片高度

    private var mRow: Int = 0
    private var mColumns: Int = 0
    private var mExpectColumn: Int = 3  //默认显示三列
    private var mImageGap = 10  //图片之前的间隙

    private var mImageBoxLoader: ImageBoxLoader? = null

    private val mImageViewCache: MutableList<AppCompatImageView> = mutableListOf()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var width = MeasureSpec.getSize(widthMeasureSpec)
        var height = MeasureSpec.getSize(heightMeasureSpec)
        if (mImageViewCache.size == 1) {
            width = mImageWith
            height = mImageHeight
        } else if (mImageViewCache.size > 1) {
            // 1 2
            // 3 4
            height = mRow * mImageHeight + mImageGap * (mRow - 1) + paddingTop + paddingBottom
        }
        setMeasuredDimension(width, height)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        //super.onLayout(changed, left, top, right, bottom)
        for (index in 0 until childCount) {
            val child = getChildAt(index)
            //child所在列
            val column = index % mExpectColumn
            //child所在行
            val row = index / mExpectColumn + if (column == 0) {
                0
            } else {
                1
            }
            val childLeft = column * (mImageWith + mImageGap) + paddingLeft
            val childRight = childLeft + mImageWith
            val childTop = row * (mImageHeight + mImageGap) + paddingTop
            val childBottom = childTop + mImageHeight
            Log.e(
                "sherry",
                "childCount:" + childCount +
                    "    index:" + index +
                    "    childLeft:" + childLeft +
                    "    childRight:" + childRight +
                    "    childTop:" + childTop +
                    "    childBottom:" + childBottom
            )
            child.layout(childLeft, childTop, childRight, childBottom)
        }
    }

    private fun getDefaultParams(width: Int = 0, height: Int = 0): LayoutParams {
        return LayoutParams(width, height)
    }

    //从缓存中获取ImageView
    private fun getImageViewFromCache(position: Int): AppCompatImageView {
        return if (mImageViewCache.size < position) {
            //直接从缓存中读取ImageView
            val cacheImageView = mImageViewCache[position]
            if (cacheImageView.parent != null) {
                //从原父布局移除
                (cacheImageView.parent as ViewGroup).removeView(cacheImageView)
            }
            cacheImageView
        } else {
            //创建新的ImageView并添加至缓存
            val newImageView = AppCompatImageView(context)
            mImageViewCache.add(newImageView)
            newImageView
        }
    }

    fun setImageLoader(loader: ImageBoxLoader) {
        mImageBoxLoader = loader
    }

    fun setImageList(list: MutableList<ImageVo>) {
        if (list.isEmpty()) {
            visibility = View.GONE
        }

        if (list.size == 1) {
            //单张图片
            mRow = 1
            mColumns = 1
            val vo = list[0]
            mImageWith = vo.width
            mImageHeight = vo.height

            val cacheImageView = getImageViewFromCache(0)
            mImageBoxLoader?.loadImage(cacheImageView, list[0], 0)
            addView(cacheImageView, getDefaultParams(mImageWith, mImageHeight))
        } else {
            //多张图片
            mColumns = mExpectColumn
            mRow = list.size / mExpectColumn + if (list.size % mExpectColumn == 0) {
                0
            } else {
                1
            }
            mImageWith = (width - ((mExpectColumn - 1) * mImageGap)) / mExpectColumn
            mImageHeight = mImageWith
            for (index in 0 until list.size) {
                val vo = list[index]
                val cacheImageView = getImageViewFromCache(index)
                mImageBoxLoader?.loadImage(cacheImageView, vo, index)
                addView(cacheImageView, getDefaultParams(mImageWith, mImageHeight))
            }
        }
    }
}