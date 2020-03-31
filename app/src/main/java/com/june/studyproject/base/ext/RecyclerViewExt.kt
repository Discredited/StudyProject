package com.june.studyproject.base.ext

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.june.studyproject.R
import com.june.studyproject.common.LinearItemDecoration

fun RecyclerView.setLinearManager(
    orientation: Int = RecyclerView.VERTICAL,
    reverse: Boolean = false
) {
    layoutManager = LinearLayoutManager(context, orientation, reverse)
}

fun RecyclerView.setGridManager(
    spanCount: Int = 2,
    orientation: Int = RecyclerView.VERTICAL,
    reverse: Boolean = false
) {
    layoutManager = GridLayoutManager(context, spanCount, orientation, reverse)
}

fun RecyclerView.addLinearItemDecoration(
    color: Int = ContextCompat.getColor(context, R.color.color_transparent),
    marginStart: Int = 0,
    marginEnd: Int = 0,
    size: Int = 1,
    orientation: Int = RecyclerView.VERTICAL
) {
    addItemDecoration(
        LinearItemDecoration(
            color,
            marginStart,
            marginEnd,
            size,
            orientation
        )
    )
}