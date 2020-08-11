package com.june.studyproject.component.index

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.june.studyproject.R
import kotlinx.android.synthetic.main.item_component.view.*

class ComponentAdapter : BaseQuickAdapter<CardExampleVo, BaseViewHolder>(R.layout.item_component) {

    override fun convert(holder: BaseViewHolder, item: CardExampleVo) {
        holder.itemView.iv_component_cover.setImageResource(item.iconRes)
        holder.itemView.tv_component_name.text = item.title
        holder.itemView.tv_component_description.text = item.description
    }
}