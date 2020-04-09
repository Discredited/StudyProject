package com.june.studyproject.component.recycler.custom

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.june.studyproject.R

class CustomLayoutManager(
    context: Context,
    orientation: Int,
    reverseLayout: Boolean
) : LinearLayoutManager(context, orientation, reverseLayout) {

    private val mItemGap = context.resources.getDimensionPixelSize(R.dimen.dp_20)

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return super.generateDefaultLayoutParams()
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State) {
        if (state.itemCount == 0 || state.isPreLayout) {
            return
        }
        //layoutChild(recycler)
    }
}