package com.june.studyproject.expand.zip

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.june.studyproject.R
import com.june.studyproject.base.ext.loadImage
import kotlinx.android.synthetic.main.item_unzip_icon.view.*

class UnzipAdapter() : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_unzip_icon) {

    override fun convert(holder: BaseViewHolder, item: String) {
        holder.itemView.ivIcon.loadImage(item)
    }
}