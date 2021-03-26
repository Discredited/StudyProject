package com.june.studyproject.expand.zip

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.june.studyproject.R
import com.june.studyproject.base.ext.loadImage
import com.june.studyproject.databinding.ItemUnzipIconBinding

class UnzipAdapter : BaseQuickAdapter<String, BaseDataBindingHolder<ItemUnzipIconBinding>>(R.layout.item_unzip_icon) {

    override fun convert(holder: BaseDataBindingHolder<ItemUnzipIconBinding>, item: String) {
        holder.dataBinding?.ivIcon?.loadImage(item)
    }
}