package com.june.rvadapter

import android.view.View

abstract class ItemViewCreator<MODEL : Any> {

    abstract fun getItemViewId(): Int

    abstract fun createViewHolder(view: View): ItemViewHolder

    abstract fun covert(item: MODEL, holder: ItemViewHolder)
}