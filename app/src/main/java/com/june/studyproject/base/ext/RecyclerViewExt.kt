package com.june.studyproject.base.ext

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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