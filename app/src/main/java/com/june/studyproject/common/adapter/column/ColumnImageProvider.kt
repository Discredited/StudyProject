package com.june.studyproject.common.adapter.column

import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.june.studyproject.R

class ColumnImageProvider: BaseItemProvider<ColumnInterface>() {
    override val itemViewType: Int
        get() = R.layout.item_column_image
    override val layoutId: Int
        get() = TODO("Not yet implemented")

    override fun convert(helper: BaseViewHolder, item: ColumnInterface) {
        TODO("Not yet implemented")
    }
}