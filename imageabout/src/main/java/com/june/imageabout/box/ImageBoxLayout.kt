package com.june.imageabout.box

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import com.june.imageabout.R

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

    private var mImageWidth: Int = 0 //图片宽度
    private var mImageHeight: Int = 0 //图片高度
    private var mExpectImageWidth: Int = 540 //宽度预估值
    private var mExpectImageHeight: Int = 720 //高度预估值
    private var mImageGap = 10  //图片之前的间隙

    //在列表中首次获取width可能为0,所以需要一个默认LayoutParams大小的预设值
    private var mExpectLayoutParamsSize: Int = 400//默认LayoutParams推荐大小

    private var mRow: Int = 0
    private var mColumn: Int = 0
    private var mExpectColumn: Int = 3  //默认显示三列
    private var mImageMax = 9  //图片最大显示数量 -1表示不限制
    private var mImageMaxOver = false //是否显示超过Max的图片数量

    private var mImageBoxLoader: ImageBoxLoader? = null

    private val mImageList: MutableList<ImageVo> = mutableListOf()
    private val mImageViewCache: MutableList<BoxImageView> = mutableListOf()

    init {
        val array = context.obtainStyledAttributes(R.styleable.ImageBoxLayout)
        try {
            mFourStyle = array.getInt(
                R.styleable.ImageBoxLayout_imageBoxFourStyle,
                FOUR_STYLE_SQUARE
            )
            mExpectColumn = array.getInt(R.styleable.ImageBoxLayout_imageBoxExpectColumn, 3)
            mImageGap = array.getDimensionPixelSize(R.styleable.ImageBoxLayout_imageBoxGap, 5)
            mImageMax = array.getInt(R.styleable.ImageBoxLayout_imageBoxMax, 9)
            mImageMaxOver = array.getBoolean(R.styleable.ImageBoxLayout_imageBoxMaxOver, true)
        } finally {
            array.recycle()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height: Int = if (mImageList.size == 1) {
            if (mImageWidth == 0) {
                mImageWidth = mExpectImageWidth
            }
            if (mImageHeight == 0) {
                mImageHeight = mExpectImageHeight
            }
            mImageHeight
        } else {
            //即使size==0 也默认显示高度样式
            if (mImageList.size == 0) {
                mRow = 1 //默认显示一行
                mColumn = 1 //默认显示一列
            }
            mImageWidth = (width - paddingStart - paddingEnd - mImageGap * (mExpectColumn - 1)) / mExpectColumn
            mImageHeight = mImageWidth
            // 1 2
            // 3 4
            mRow * mImageHeight + mImageGap * (mRow - 1) + paddingTop + paddingBottom
        }
        //Timber.e("measure=>w:$width,$height")
        setMeasuredDimension(width, height)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        for (index in 0 until childCount) {
            val child = getChildAt(index)
            //child所在列
            val column = index % mColumn
            //child所在行
            val row = index / mColumn

            val childLeft = column * (mImageWidth + mImageGap) + paddingLeft
            val childRight = childLeft + mImageWidth
            val childTop = row * (mImageHeight + mImageGap) + paddingTop
            val childBottom = childTop + mImageHeight
//            Timber.e(
//                "layout=>childCount:$childCount    index:$index    childLeft:$childLeft    childRight:$childRight    childTop:$childTop    childBottom:$childBottom"
//            )
            child.layout(childLeft, childTop, childRight, childBottom)
        }
    }

    private fun getDefaultParams(width: Int = mExpectLayoutParamsSize,
                                 height: Int = mExpectLayoutParamsSize): LayoutParams {
        return LayoutParams(width, height)
    }

    //从缓存中获取ImageView
    private fun getImageViewFromCache(position: Int): BoxImageView {
        return if (position < mImageViewCache.size) {
            //直接从缓存中读取ImageView
            val cacheImageView = mImageViewCache[position]
            if (cacheImageView.parent != null) {
                //从原父布局移除
                (cacheImageView.parent as ViewGroup).removeView(cacheImageView)
            }
            cacheImageView.layoutParams?.width = 0
            cacheImageView.layoutParams?.height = 0
            cacheImageView.resetMaxOver()
            //Timber.e("position:$position    cacheImageView直接从缓存中读取  ${mImageViewCache.size}")
            cacheImageView
        } else {
            //创建新的ImageView并添加至缓存
            val newImageView = BoxImageView(context)
            newImageView.scaleType = ImageView.ScaleType.CENTER_CROP
            mImageViewCache.add(newImageView)
            //Timber.e("position:$position    newImageView创建新的ImageView  ${mImageViewCache.size}")
            newImageView.layoutParams?.width = 0
            newImageView.layoutParams?.height = 0
            newImageView.resetMaxOver()
            newImageView
        }
    }

    private fun getImageSize(imageSize: Int): Int {
        return if (mImageMax != -1 && imageSize > mImageMax) {
            mImageMax
        } else {
            imageSize
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

//        Timber.e("----------------------------------------")
//        Timber.e("当前ImageVo数量：${list.size}")
        removeAllViews()

        //获取行列
        val imageSize = getImageSize(list.size)
        val rowColumn = getRowColumn(imageSize, mFourStyle)
        val overCount = if (mImageMax != -1) {
            list.size - mImageMax
        } else {
            0
        }
        if (imageSize == 1) {
            //单张图片
            mRow = rowColumn[0]
            mColumn = rowColumn[1]
            val vo = list[0]
            mImageWidth = vo.width
            mImageHeight = vo.height

            val cacheImageView = getImageViewFromCache(0)
            addView(cacheImageView, getDefaultParams(mImageWidth, mImageHeight))
            mImageBoxLoader?.loadImage(cacheImageView, list[0], 0)
        } else {
            //多张图片
            mRow = rowColumn[0]
            mColumn = rowColumn[1]

            //在列表中首次加载该布局时，getWidth == 0
            //此时只能使用默认的mExpectLayoutParamsSize推荐值大小
            //当布局复用能够获取到getWidth后，再重置mExpectLayoutParamsSize为正确值
            if (width != 0) {
                mExpectLayoutParamsSize = (width - paddingStart - paddingEnd - mImageGap * (mExpectColumn - 1)) / mExpectColumn
            }

            for (index in 0 until imageSize) {
                val vo = list[index]
                val cacheImageView = getImageViewFromCache(index)
                if (index == imageSize - 1 && mImageMaxOver && overCount > 0) {
                    cacheImageView.setMaxOver(overCount, mImageMaxOver)
                }
                addView(
                    cacheImageView,
                    getDefaultParams(mExpectLayoutParamsSize, mExpectLayoutParamsSize)
                )
                mImageBoxLoader?.loadImage(cacheImageView, vo, index)
            }
        }
        //Timber.e("当前ImageWidth:$mImageWidth  ImageHeight:$mImageHeight    width:$width,height:$height")

        mImageList.clear()
        mImageList.addAll(list)
    }
}