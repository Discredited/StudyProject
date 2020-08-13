package com.june.studyproject.common.adapter.column

class ColumnTitleVo(val title: String) : ColumnInterface {
    override fun getType(): Int = ColumnInterface.COLUMN_TITLE
}