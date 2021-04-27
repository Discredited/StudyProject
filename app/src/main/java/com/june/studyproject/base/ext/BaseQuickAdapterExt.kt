package com.june.studyproject.base.ext

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.chad.library.adapter.base.listener.OnItemClickListener


inline fun BaseQuickAdapter<*, *>.itemClick(crossinline block: (BaseQuickAdapter<*, *>, View, Int) -> Unit) {
    this.setOnItemClickListener(object : OnItemClickListener {
        private var mLastClickTime = 0L
        override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
            val currentClickTime = System.currentTimeMillis()
            if (currentClickTime - mLastClickTime < 1000) {
                return
            }
            mLastClickTime = System.currentTimeMillis()

            block(adapter, view, position)
        }
    })
}

inline fun BaseQuickAdapter<*, *>.itemChildClick(crossinline block: (BaseQuickAdapter<*, *>, View, Int) -> Unit) {
    this.setOnItemChildClickListener(object : OnItemChildClickListener {
        private var mLastClickTime = 0L
        override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
            val currentClickTime = System.currentTimeMillis()
            if (currentClickTime - mLastClickTime < 1000) {
                return
            }
            mLastClickTime = System.currentTimeMillis()

            block(adapter, view, position)
        }
    })
}