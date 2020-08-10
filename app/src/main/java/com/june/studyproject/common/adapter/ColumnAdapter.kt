package com.june.studyproject.common.adapter

import com.chad.library.adapter.base.BaseProviderMultiAdapter

class ColumnAdapter : BaseProviderMultiAdapter<ColumnInterface>() {

    override fun getItemType(data: List<ColumnInterface>, position: Int): Int = data[position].getType()
}