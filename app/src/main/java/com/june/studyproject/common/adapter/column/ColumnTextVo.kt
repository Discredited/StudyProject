package com.june.studyproject.common.adapter.column

class ColumnTextVo(val content: String) : ColumnInterface {
    override fun getType(): Int = ColumnInterface.COLUMN_TEXT
}