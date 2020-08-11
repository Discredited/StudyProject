package com.june.studyproject.common.adapter.column

import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.june.studyproject.R

class ColumnTextProvider : BaseItemProvider<ColumnInterface>() {

    override val itemViewType: Int
        get() = ColumnInterface.COLUMN_TEXT

    override val layoutId: Int
        get() = R.layout.item_column_text

    override fun convert(helper: BaseViewHolder, item: ColumnInterface) {

    }
}