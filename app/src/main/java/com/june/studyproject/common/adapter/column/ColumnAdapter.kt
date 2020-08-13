package com.june.studyproject.common.adapter.column

import com.chad.library.adapter.base.BaseProviderMultiAdapter

class ColumnAdapter : BaseProviderMultiAdapter<ColumnInterface>() {

    init {
        addItemProvider(ColumnTitleProvider())
        addItemProvider(ColumnTextProvider())
        addItemProvider(ColumnImageProvider())
    }

    override fun getItemType(data: List<ColumnInterface>, position: Int): Int = data[position].getType()
}