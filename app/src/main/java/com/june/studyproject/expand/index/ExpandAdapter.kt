package com.june.studyproject.expand.index

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.june.studyproject.R
import com.june.studyproject.base.ext.loadCircleImage
import com.june.studyproject.base.ext.loadRoundImage
import com.june.studyproject.component.index.CardExampleVo
import com.june.studyproject.databinding.ItemExpandBinding

class ExpandAdapter : BaseQuickAdapter<CardExampleVo, BaseDataBindingHolder<ItemExpandBinding>>(R.layout.item_expand) {

    override fun convert(holder: BaseDataBindingHolder<ItemExpandBinding>, item: CardExampleVo) {
        holder.dataBinding?.tvExpandName?.text = item.title
        holder.dataBinding?.tvExpandDescription?.text = item.description
        holder.dataBinding?.ivExpandAvatar?.loadCircleImage(item.iconUrl)
        holder.dataBinding?.ivExpandCover?.loadRoundImage(item.iconUrl)
    }
}