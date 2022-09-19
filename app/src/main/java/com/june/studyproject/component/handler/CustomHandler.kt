package com.june.studyproject.component.handler

import android.os.Handler
import android.os.Looper
import android.os.Message

/**
 * 自定义Handler
 *
 * 2022/9/17
 * @author June
 */
class CustomHandler(looper: Looper, callback: Callback?) : Handler(looper, callback) {

    override fun handleMessage(msg: Message) {
        // 消息处理逻辑
    }
}