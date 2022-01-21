package com.june.studyproject.component.recycler.rvadapter.vo

interface NormalInterface {

    fun getType(): Int

    companion object {
        const val TYPE_SIMPLE_TEXT = 0
        const val TYPE_SIMPLE_IMAGE = 1
        const val TYPE_MULTI_TEXT = 2
        const val TYPE_MULTI_IMAGE = 3
    }
}