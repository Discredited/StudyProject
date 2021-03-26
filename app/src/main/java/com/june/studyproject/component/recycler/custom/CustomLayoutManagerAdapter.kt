package com.june.studyproject.component.recycler.custom

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.june.studyproject.R
import com.june.studyproject.databinding.ItemCustomLayoutCardBinding

class CustomLayoutManagerAdapter : BaseQuickAdapter<Int, BaseDataBindingHolder<ItemCustomLayoutCardBinding>>(R.layout.item_custom_layout_card) {
    override fun convert(holder: BaseDataBindingHolder<ItemCustomLayoutCardBinding>, item: Int) {
        holder.dataBinding?.cvCard?.setCardBackgroundColor(item)
        holder.dataBinding?.tvCardPosition?.text = holder.layoutPosition.toString()
    }

}