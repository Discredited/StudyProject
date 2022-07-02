package com.june.base.basic.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridItemDecoration(
    private val column: Int = 2, //列数
    private val space: Int = 10 //线宽
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val currentColumn = position % column

        if (position < column) {
            outRect.top = 0
        } else {
            outRect.top = space
        }

        outRect.left = currentColumn * space / column
        outRect.right = space - (currentColumn + 1) * space / column
    }
}