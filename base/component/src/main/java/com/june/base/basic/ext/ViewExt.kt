package com.june.base.basic.ext

import android.os.SystemClock
import android.view.View

inline fun View.click(debounceTime: Long = 500, crossinline block: (View) -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {

        private var lastClickTime: Long = 0

        override fun onClick(v: View) {

            if (debounceTime == 0.toLong()) {
                block(v)
                return
            }

            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
            else block(v)
            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}