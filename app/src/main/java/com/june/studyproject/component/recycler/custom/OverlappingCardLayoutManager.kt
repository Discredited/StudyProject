package com.june.studyproject.component.recycler.custom

import androidx.recyclerview.widget.RecyclerView

class OverlappingCardLayoutManager : RecyclerView.LayoutManager() {
    /**
     * 与ViewGroup显示View类似
     * 1.添加 通过addView方法把子View添加进ViewGroup
     * 2.测量 重写onMeasure方法并在这里决定自身尺寸以及每一个子View大小
     * 3.布局 重写onLayout方法，在里面调用子View的layout方法来确定它的位置和尺寸
     *
     * 在自定义LayoutManager中
     * 1.进行布局之前，我们需要调用detachAndScrapAttachedViews方法把屏幕中的Items都分离出来，内部调整好位置和数据后，再把它添加回去(如果需要的话)
     * 2.分离了之后，我们就要想办法把它们再添加回去了，所以需要通过addView方法来添加，那这些View在哪里得到呢？ 我们需要调用 Recycler的getViewForPosition(int position) 方法来获取
     * 3.获取到Item并重新添加了之后，我们还需要对它进行测量，这时候可以调用measureChild或measureChildWithMargins方法，两者的区别我们已经了解过了，相信同学们都能根据需求选择更合适的方法
     * 4.在测量完还需要做什么呢？ 没错，就是布局了，我们也是根据需求来决定使用layoutDecorated还是layoutDecoratedWithMargins方法
     * 5.在自定义ViewGroup中，layout完就可以运行看效果了，但在LayoutManager还有一件非常重要的事情，就是回收了，我们在layout之后，还要把一些不再需要的Items回收，以保证滑动的流畅度
     *
     * Recycler的回收
     * -可直接重用的临时缓存：mAttachedScrap，mChangedScrap
     *         mChangedScrap只能在预布局状态下重用
     *         mAttachedScrap则可以在非预布局状态下重用
     * -可直接重用的缓存：mCachedViews
     * -需重新绑定数据的缓存：mRecyclerPool.mScrap
     *
     *
     */

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT)
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State) {
        if (state.itemCount == 0) {
            //在item为0时，移除所有的缓存
            removeAndRecycleAllViews(recycler)
            return
        }

        // 分离全部已有的view 放入临时缓存  mAttachedScrap 集合中
        detachAndScrapAttachedViews(recycler)

        layoutChildren(recycler, state, 0)
    }

    private fun layoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State, dx: Int) {
        for (position in 0 until itemCount) {
            val childView = recycler.getViewForPosition(position)
            val measureWidth = measureChildWithMargins(childView, 0, 0)
        }
    }
}