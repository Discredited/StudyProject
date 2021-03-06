package com.june.studyproject.common.adapter.column

import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.june.studyproject.R

class ColumnTitleProvider : BaseItemProvider<ColumnInterface>() {

    override val itemViewType: Int
        get() = ColumnInterface.COLUMN_TITLE

    override val layoutId: Int
        get() = R.layout.item_column_title

    override fun convert(helper: BaseViewHolder, item: ColumnInterface) {
        val data = item as ColumnTitleVo
        helper.setText(R.id.tvColumnTitle, data.title)
    }
}