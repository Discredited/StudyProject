package com.june.rvadapter

import androidx.viewbinding.ViewBinding

/**
 * Item抽象接口
 *
 * @author June
 * @time 2024/5/17
 */
interface IItem {
    fun getViewBinding(): ViewBinding

    fun dataBind(binding: ViewBinding, position: Int)
}

abstract class BaseItem() : IItem {

    abstract fun itemClick(position: Int)
}