package com.june.studyproject.component.index

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.june.studyproject.R
import com.june.studyproject.databinding.ItemComponentBinding

class ComponentAdapter : BaseQuickAdapter<CardExampleVo, BaseDataBindingHolder<ItemComponentBinding>>(R.layout.item_component) {
    override fun convert(holder: BaseDataBindingHolder<ItemComponentBinding>, item: CardExampleVo) {
        holder.dataBinding?.apply {
            ivComponentCover.setImageResource(item.iconRes)
            tvComponentName.text = item.title
            tvComponentDescription.text = item.description
        }
    }

}