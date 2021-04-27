package com.june.base.basic.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridItemDecoration(
    var mColumn: Int = 2, //列数
    var mSpace: Int = 10 //线宽
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % mColumn

        if (position < mColumn) {
            outRect.top = 0
        } else {
            outRect.top = mSpace
        }

        outRect.left = column * mSpace / mColumn
        outRect.right = mSpace - (column + 1) * mSpace / mColumn
    }
}