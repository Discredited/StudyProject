package com.june.studyproject.component.recycler.custom

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.floor
import kotlin.math.pow

/**
 * @author <a href="mailto:xujun@snqu.com">徐俊</a>
 * @description 自定义LayoutManger
 * @version 1.0.0
 * @time 2020/4/9 16:16
 */
class CustomLayoutManager(
    context: Context,
    orientation: Int = RecyclerView.VERTICAL,
    reverseLayout: Boolean = false
) : LinearLayoutManager(context, orientation, reverseLayout) {
    /**
     * - generateDefaultLayoutParams
     * 指定默认的LayoutParams，一般返回 return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT,RecyclerView.LayoutParams.WRAP_CONTENT); 就可以了
     *
     * - onLayoutChildren
     * 测量Item的信息
     *
     * - removeAndRecycleView or detachAndScrapAttachedViews...等等相关方法
     * 处理Item回收，使用RecyclerView的二级缓存机制就可以，多方便
     *
     * - canScrollVertically or canScrollHorizontally
     * 滑动处理逻辑
     */

    private var mMeasureWidth: Int = 0
    private var mMeasureHeight: Int = 0

    private var mItemViewWidth: Int = 0  //itemView宽度
    private var mItemViewHeight: Int = 0  //itemView高度
    private var mItemCount: Int = 0 //item数量
    private var mScrollOffset: Int = Int.MAX_VALUE
    private val mScale = 0.9

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State) {
        if (state.itemCount == 0 || state.isPreLayout) return
        removeAndRecycleAllViews(recycler)

        if (mMeasureWidth == 0 || mMeasureHeight == 0) {
            mMeasureWidth = width - paddingStart - paddingEnd
            mMeasureHeight = height - paddingTop - paddingBottom
        }

        mItemViewWidth = (mMeasureWidth * 0.95f).toInt()
        mItemViewHeight = (mItemViewWidth * 1.5f).toInt()
        mItemCount = itemCount
        mScrollOffset = mItemViewHeight.coerceAtLeast(mScrollOffset).coerceAtMost(mItemCount * mItemViewHeight)

        layoutChild(recycler)
    }

    private fun layoutChild(recycler: RecyclerView.Recycler) {
        if (itemCount == 0) return

        var bottomItemPosition = floor(mScrollOffset.toDouble() / mItemViewHeight).toInt()
        var remainSpace = mMeasureHeight - mItemViewHeight

        val bottomItemVisibleHeight = mScrollOffset % mItemViewHeight
        val offsetPercentRelativeToItemView = bottomItemVisibleHeight * 1.0f / mItemViewHeight

        val layoutInfoList = ArrayList<ItemViewInfo>()
        var position = bottomItemPosition - 1
        var j = 1
        while (position >= 0) {
            val maxOffset: Double = (mMeasureHeight - mItemViewHeight) / 2 * 0.8.pow(j.toDouble())
            val start = (remainSpace - offsetPercentRelativeToItemView * maxOffset).toInt()
            val scaleXY = (mScale.pow(j - 1.toDouble()) * (1 - offsetPercentRelativeToItemView * (1 - mScale))).toFloat()
            val layoutPercent: Float = start * 1F / mMeasureHeight
            val info = ItemViewInfo(start, scaleXY, offsetPercentRelativeToItemView, layoutPercent)
            layoutInfoList.add(0, info)
            remainSpace = (remainSpace - maxOffset).toInt()
            if (remainSpace <= 0) {
                ItemViewInfo(
                    (remainSpace + maxOffset).toInt(),
                    0F,
                    (info.top / mMeasureHeight).toFloat(),
                    mScale.pow(j - 1.toDouble()).toFloat()
                )
                break
            }
            position--
            j++
        }

        if (bottomItemPosition < mItemCount) {
            val start: Int = mMeasureHeight - bottomItemVisibleHeight
            layoutInfoList.add(
                ItemViewInfo(
                    start,
                    1.0f,
                    bottomItemVisibleHeight * 1.0f / mItemViewHeight,
                    start * 1.0f / mMeasureHeight,
                    true
                )
            )
        } else {
            bottomItemPosition -= 1 //99
        }

        val layoutCount: Int = layoutInfoList.size
        val startPos = bottomItemPosition - (layoutCount - 1)
        val endPos = bottomItemPosition
        val childCount = childCount
        for (index in childCount - 1 downTo 0) {
            val childView = getChildAt(index)
            childView?.let {
                val pos = getPosition(it)
                if (pos > endPos || pos < startPos) {
                    removeAndRecycleView(it, recycler)
                }
            }
        }

        detachAndScrapAttachedViews(recycler)

        for (i in 0 until layoutCount) {
            val view = recycler.getViewForPosition(startPos + i)
            val layoutInfo: ItemViewInfo = layoutInfoList[i]
            addView(view)
            measureChildWithExactlySize(view)
            val left: Int = (mMeasureWidth - mItemViewWidth) / 2
            layoutDecoratedWithMargins(view, left, layoutInfo.top, left + mItemViewWidth, layoutInfo.top + mItemViewHeight)
            view.pivotX = view.width / 2.toFloat()
            view.pivotY = 0f
            view.scaleX = layoutInfo.scaleXY
            view.scaleY = layoutInfo.scaleXY
        }
    }

    override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler, state: RecyclerView.State): Int {
        val pendingScrollOffset = mScrollOffset + dy
        mScrollOffset = mItemViewHeight.coerceAtLeast(mScrollOffset + dy).coerceAtMost(mItemCount * mItemViewHeight)
        layoutChild(recycler)
        return mScrollOffset - pendingScrollOffset + dy
    }

    private fun measureChildWithExactlySize(child: View) {
        val widthSpec = View.MeasureSpec.makeMeasureSpec(mItemViewWidth, View.MeasureSpec.EXACTLY)
        val heightSpec = View.MeasureSpec.makeMeasureSpec(mItemViewHeight, View.MeasureSpec.EXACTLY)
        child.measure(widthSpec, heightSpec)
    }
}