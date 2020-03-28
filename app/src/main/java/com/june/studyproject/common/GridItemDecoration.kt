package com.june.studyproject.common

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridItemDecoration(
    var mColumn: Int = 2, //列数
    var mSpace: Int = 30 //线宽
) : RecyclerView.ItemDecoration() {

    private var mItemSpace: Int = (mSpace * (mColumn - 1)) / mColumn //每个Item应该均分的宽度

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)

        when {
            (position + 1) % mColumn == 1 -> {
                //第一列
                outRect.left = 0
                outRect.right = mItemSpace
            }
            (position + 1) % mColumn == 0 -> {
                //最后一列
                outRect.left = mItemSpace
                outRect.right = 0
            }
            else -> {
                //中间列
                outRect.left = mItemSpace / 2
                outRect.right = mItemSpace / 2
            }
        }

        if (position < mColumn) {
            outRect.top = 0
        } else {
            outRect.top = mSpace
        }
    }
}