package com.june.studyproject.common.adapter.column

class ColumnImageVo(val url: String) : ColumnInterface {
    override fun getType(): Int = ColumnInterface.COLUMN_IMAGE
}