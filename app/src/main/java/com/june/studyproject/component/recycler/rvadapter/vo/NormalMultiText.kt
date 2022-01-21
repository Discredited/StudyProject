package com.june.studyproject.component.recycler.rvadapter.vo

import com.june.studyproject.component.recycler.rvadapter.vo.NormalInterface.Companion.TYPE_MULTI_TEXT

class NormalMultiText(
    val title: String,
    val content: String,
    val subContent: String
) : NormalInterface {
    override fun getType(): Int = TYPE_MULTI_TEXT
}