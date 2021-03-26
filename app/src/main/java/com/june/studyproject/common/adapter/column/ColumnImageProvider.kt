package com.june.studyproject.common.adapter.column

import androidx.appcompat.widget.AppCompatImageView
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.june.studyproject.R
import com.june.studyproject.base.ext.loadImage

class ColumnImageProvider : BaseItemProvider<ColumnInterface>() {
    override val itemViewType: Int
        get() = ColumnInterface.COLUMN_IMAGE
    override val layoutId: Int
        get() = R.layout.item_column_image

    override fun convert(helper: BaseViewHolder, item: ColumnInterface) {
        val data = item as ColumnImageVo
        helper.itemView.findViewById<AppCompatImageView>(R.id.ivColumnImage).loadImage(data.url)
    }
}