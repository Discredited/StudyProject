package com.june.studyproject.component.recycler.custom

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.june.studyproject.R
import kotlinx.android.synthetic.main.item_custom_layout_card.view.*

class CustomLayoutManagerAdapter : BaseQuickAdapter<Int, BaseViewHolder>(R.layout.item_custom_layout_card) {
    override fun convert(helper: BaseViewHolder, item: Int) {
        helper.itemView.cvCard.setCardBackgroundColor(item)
    }
}