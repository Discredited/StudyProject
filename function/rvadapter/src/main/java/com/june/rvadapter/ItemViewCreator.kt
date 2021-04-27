package com.june.rvadapter

import android.view.View

abstract class ItemViewCreator<MODEL> {

    abstract fun getItemViewId(): Int

    abstract fun createViewHolder(view: View): ItemViewHolder<MODEL>

    abstract fun covert(item: MODEL, holder: ItemViewHolder<MODEL>)
}