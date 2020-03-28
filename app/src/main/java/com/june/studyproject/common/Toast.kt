package com.june.studyproject.common

import com.blankj.utilcode.util.ToastUtils

object Toast {
    fun showShort(textRes: Int) {
        ToastUtils.showShort(textRes)
    }

    fun showShort(text: String) {
        ToastUtils.showShort(text)
    }
}