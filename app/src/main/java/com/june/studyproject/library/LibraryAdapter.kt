package com.june.studyproject.library

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.june.studyproject.R
import com.june.studyproject.component.index.CardExampleVo
import com.june.studyproject.databinding.ItemLibraryBinding

class LibraryAdapter : BaseQuickAdapter<CardExampleVo, BaseDataBindingHolder<ItemLibraryBinding>>(R.layout.item_library) {

    override fun convert(holder: BaseDataBindingHolder<ItemLibraryBinding>, item: CardExampleVo) {
        holder.dataBinding?.ivLibraryCover?.setImageResource(item.iconRes)
        holder.dataBinding?.tvLibraryName?.text = item.title
    }
}