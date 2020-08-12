package com.june.rvadapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class ItemViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun <K> onBind(item: K, position: Int, itemList: MutableList<Any>)

}