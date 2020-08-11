package com.june.studyproject.component.recycler.rvadapter.normal.vo

import com.june.studyproject.component.recycler.rvadapter.normal.vo.NormalInterface.Companion.TYPE_MULTI_TEXT

class NormalMultiText(
    val title: String,
    val content: String,
    val subContent: String
) : NormalInterface {
    override fun getType(): Int = TYPE_MULTI_TEXT
}