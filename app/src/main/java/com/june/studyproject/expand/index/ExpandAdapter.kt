package com.june.studyproject.expand.index

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.june.studyproject.R
import com.june.studyproject.base.ext.loadCircleImage
import com.june.studyproject.base.ext.loadRoundImage
import com.june.studyproject.component.index.CardExampleVo
import kotlinx.android.synthetic.main.item_expand.view.*

class ExpandAdapter : BaseQuickAdapter<CardExampleVo, BaseViewHolder>(R.layout.item_expand) {
    override fun convert(helper: BaseViewHolder, item: CardExampleVo) {
        helper.itemView.tvExpandName.text = item.title
        helper.itemView.tvExpandDescription.text = item.description
        helper.itemView.ivExpandAvatar.loadCircleImage(item.iconUrl)
        helper.itemView.ivExpandCover.loadRoundImage(item.iconUrl)
    }
}