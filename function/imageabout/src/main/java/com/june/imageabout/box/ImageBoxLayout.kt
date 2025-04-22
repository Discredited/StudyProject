package com.june.imageabout.box

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.june.studyproject.imageabout.R

/**
 * ImageBoxLayout
 * 图片九宫格展示控件
 * 设置4张图片的显示样式
 * 设置单张图片自定义宽高(ImageLoader)
 * 设置
 */
class ImageBoxLayout<T> @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    companion object {
        // 1 2 3
        // 4
        const val FOUR_STYLE_NORMAL = 0

        // 1 2
        // 3 4
        const val FOUR_STYLE_SQUARE = 1

        const val SINGLE_STYLE_FIXED = 0  //固定宽高(1:1)显示
        const val SINGLE_STYLE_SCALE = 1  //按比例显示
    }

    private var mFourStyle: Int = 0

    private var mImageWidth: Int = 0 //图片宽度
    private var mImageHeight: Int = 0 //图片高度
    private var mExpectImageWidth: Int = 0 //宽度预估值
    private var mExpectImageHeight: Int = 0 //高度预估值
    private var mImageGap = 10  //图片之前的间隙
    private var mSingleStyle: Int = 0 //单张图片的显示类型
    private var mSingleFixedColumn: Boolean = false //固定列数  及单张图片时也按照列数设置宽高

    //在列表中首次获取width可能为0,所以需要一个默认LayoutParams大小的预设值
    private var mExpectLayoutParamsSize: Int = 300//默认LayoutParams推荐大小

    private var mRow: Int = 0
    private var mColumn: Int = 0
    private var mExpectColumn: Int = 3  //默认显示三列

    private var mImageMax = 9  //图片最大显示数量 -1表示不限制
    private var mImageMaxOver = false //是否显示超过Max的图片数量

    private var mImageRadius: Float = 0F  //图片圆角

    private var mImageBoxLoader: ImageBoxLoader<T>? = null

    private var mImageList: MutableList<T> = mutableListOf()
    private val mImageViewCache: MutableList<BoxImageView> = mutableListOf()

    init {
        val array = context.obtainStyledAttributes(
            attrs,
            R.styleable.ImageBoxLayout,
            defStyleAttr,
            0
        )
        try {
            mFourStyle = array.getInt(
                R.styleable.ImageBoxLayout_imageBoxFourStyle,
                FOUR_STYLE_SQUARE
            )
            mExpectColumn = array.getInt(R.styleable.ImageBoxLayout_imageBoxExpectColumn, 3)
            mImageGap = array.getDimensionPixelSize(R.styleable.ImageBoxLayout_imageBoxGap, 5)
            mImageMax = array.getInt(R.styleable.ImageBoxLayout_imageBoxMax, 9)
            mImageMaxOver = array.getBoolean(R.styleable.ImageBoxLayout_imageBoxMaxOver, false)
            mSingleFixedColumn = array.getBoolean(
                R.styleable.ImageBoxLayout_imageBoxFixedColumn,
                false
            )
            mImageRadius = array.getDimension(R.styleable.ImageBoxLayout_imageBoxRadius, 0F)
            mExpectImageWidth = array.getDimensionPixelSize(
                R.styleable.ImageBoxLayout_imageBoxSingleWidth,
                0
            )
            mExpectImageHeight = array.getDimensionPixelSize(
                R.styleable.ImageBoxLayout_imageBoxSingleHeight,
                0
            )
            mSingleStyle = array.getInt(
                R.styleable.ImageBoxLayout_imageBoxSingleStyle,
                SINGLE_STYLE_FIXED
            )
        } finally {
            array.recycle()
        }

        //单张图片推荐宽度
        if (mExpectImageWidth == 0) {
            mExpectImageWidth = 540
        }
        //单张图片推荐高度
        if (mExpectImageHeight == 0) {
            mExpectImageHeight = 540
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height: Int = if (mImageList.size == 1) {
            if (mSingleFixedColumn) {
                mImageWidth = (width - paddingStart - paddingEnd - mImageGap * (mExpectColumn - 1)) / mExpectColumn
                mImageHeight = mImageWidth
            } else {
                if (mImageWidth == 0 || mImageHeight == 0) {
                    mImageWidth = mExpectImageWidth
                    mImageHeight = mExpectImageHeight
                }
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
            child.layout(childLeft, childTop, childRight, childBottom)
//            Timber.e(
//                "layout=>childCount:$childCount    index:$index    row:$row    column:$column    childLeft:$childLeft    childRight:$childRight    childTop:$childTop    childBottom:$childBottom    width:${child.width}    height:width:${child.height} "
//            )
        }
    }

    private fun getDefaultParams(
        width: Int = mExpectLayoutParamsSize,
        height: Int = mExpectLayoutParamsSize
    ): LayoutParams {
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
            cacheImageView.resetMaxOver()
            cacheImageView
        } else {
            //创建新的ImageView并添加至缓存
            val newImageView = BoxImageView(context)
            mImageViewCache.add(newImageView)
            newImageView.setCorner(mImageRadius, invalidate = false)
            newImageView.setOnClickListener {
                if (position >= 0 && position < mImageList.size) {
                    mImageBoxLoader?.clickImage(newImageView, mImageList, position)
                }
            }
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

    fun setImageLoader(loader: ImageBoxLoader<T>) {
        mImageBoxLoader = loader
    }

    fun setImageList(list: MutableList<T>) {
        if (list.isEmpty()) {
            visibility = View.GONE
        }

        removeAllViews()

        mImageList = list

        //获取行列
        val imageSize = getImageSize(list.size)
        val rowColumn = getRowColumn(imageSize, mFourStyle)
        val overCount = if (mImageMax != -1) {
            list.size - mImageMax
        } else {
            0
        }

        mRow = rowColumn[0]
        mColumn = rowColumn[1]

        if (imageSize == 1) {
            //单张图片
            //val vo = list[0]
            if (mSingleFixedColumn) {
                mImageWidth = (width - paddingStart - paddingEnd - mImageGap * (mExpectColumn - 1)) / mExpectColumn
                mImageHeight = mImageWidth
            } else {
                mImageWidth = mImageBoxLoader?.singleImageWidth(list[0], mSingleStyle) ?: 0
                mImageHeight = mImageBoxLoader?.singleImageHeight(list[0], mSingleStyle) ?: 0
            }
            //如果宽高为0 设置推荐宽高
            if (mImageWidth <= 0 || mImageHeight <= 0) {
                mImageWidth = mExpectImageWidth
                mImageHeight = mExpectImageHeight
            }

            val cacheImageView = getImageViewFromCache(0)
            addView(cacheImageView, getDefaultParams(mImageWidth, mImageHeight))
            mImageBoxLoader?.loadImage(cacheImageView, list[0], 0, mImageWidth, mImageHeight)
        } else {
            //多张图片

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
                mImageBoxLoader?.loadImage(
                    cacheImageView,
                    vo,
                    index,
                    mExpectLayoutParamsSize,
                    mExpectLayoutParamsSize
                )
            }
        }
    }

    //设置四张图的样式
    fun setFourStyle(style: Int) {
        mFourStyle = style
        val rowColumn = getRowColumn(mImageList.size, mFourStyle)
        mRow = rowColumn[0]
        mColumn = rowColumn[1]
    }

    //修改期望列数值
    fun setExpectColumn(column: Int) {
        mExpectColumn = column
        val rowColumn = getRowColumn(mImageList.size, mFourStyle)
        mRow = rowColumn[0]
        mColumn = rowColumn[1]
    }

    //修改图片间距
    fun setImageGap(gap: Int) {
        mImageGap = gap
    }

    //修改图片圆角
    fun setImageRadius(radius: Float) {
        mImageRadius = radius
        mImageViewCache.forEach {
            it.setCorner(radius)
        }
    }

    //设置最大限制张数
    fun setImageMax(max: Int) {
        mImageMax = max
    }

    //设置是否显示MaxLimitCover
    fun setImageMaxCover(isMaxCover: Boolean) {
        mImageMaxOver = isMaxCover
    }

    fun getImageList(): MutableList<T> = mImageList
}