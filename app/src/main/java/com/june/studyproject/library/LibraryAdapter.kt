package com.june.studyproject.library

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.june.studyproject.R
import com.june.studyproject.component.index.CardExampleVo
import kotlinx.android.synthetic.main.item_library.view.*

class LibraryAdapter : BaseQuickAdapter<CardExampleVo, BaseViewHolder>(R.layout.item_library) {
    override fun convert(helper: BaseViewHolder, item: CardExampleVo) {
        helper.itemView.iv_library_cover.setImageResource(item.iconRes)
        helper.itemView.tv_library_name.text = item.title
    }
}