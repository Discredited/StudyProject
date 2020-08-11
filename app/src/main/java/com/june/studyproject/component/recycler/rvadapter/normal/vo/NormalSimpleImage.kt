package com.june.studyproject.component.recycler.rvadapter.normal.vo

import com.june.studyproject.component.recycler.rvadapter.normal.vo.NormalInterface.Companion.TYPE_SIMPLE_IMAGE

class NormalSimpleImage(
    val title: String,
    val imageUrl: String
) : NormalInterface {
    override fun getType(): Int = TYPE_SIMPLE_IMAGE
}