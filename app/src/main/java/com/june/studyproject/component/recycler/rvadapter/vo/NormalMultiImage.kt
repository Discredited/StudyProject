package com.june.studyproject.component.recycler.rvadapter.vo

import com.june.studyproject.component.recycler.rvadapter.vo.NormalInterface.Companion.TYPE_MULTI_IMAGE

class NormalMultiImage(
    val title: String,
    val imageUrl1: String,
    val imageUrl2: String
) : NormalInterface {
    override fun getType(): Int = TYPE_MULTI_IMAGE
}