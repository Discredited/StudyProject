package com.june.studyproject.common.adapter.column

interface ColumnInterface {

    fun getType(): Int

    companion object {
        const val COLUMN_TEXT = 1
        const val COLUMN_IMAGE = 2
        const val COLUMN_VIDEO = 3
    }
}