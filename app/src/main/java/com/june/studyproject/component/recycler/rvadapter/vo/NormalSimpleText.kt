package com.june.studyproject.component.recycler.rvadapter.vo

import com.june.studyproject.component.recycler.rvadapter.vo.NormalInterface.Companion.TYPE_SIMPLE_TEXT

class NormalSimpleText(
    val title: String,
    val content: String
) : NormalInterface {
    override fun getType(): Int = TYPE_SIMPLE_TEXT
}