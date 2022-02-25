package com.june.rvadapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun itemViewId(): Int
}