package com.june.studyproject.component.recycler.custom

import androidx.recyclerview.widget.RecyclerView

class OverlappingCardLayoutManager : RecyclerView.LayoutManager() {

    //
    private var mOnceCompleteScrollLength = -1

    //第一个子View滑出屏幕的偏移量
    private var mFirstChildCompleteScrollLength = -1

    //垂直方向累计偏移量
    private var mVerticalOffset = 0

    //View之间的间隙
    private var mViewGap = 0

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT)
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State) {
        if (state.itemCount == 0) {
            removeAndRecycleAllViews(recycler)
            return
        }

        mOnceCompleteScrollLength = -1
    }

}