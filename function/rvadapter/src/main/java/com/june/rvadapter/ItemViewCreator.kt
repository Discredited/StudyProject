package com.june.rvadapter

import android.view.View

abstract class ItemViewCreator<in T> {

    abstract fun getItemViewId(): Int

    abstract fun createViewHolder(view: View): ItemViewHolder

    abstract fun covert(item: T, holder: ItemViewHolder)
}