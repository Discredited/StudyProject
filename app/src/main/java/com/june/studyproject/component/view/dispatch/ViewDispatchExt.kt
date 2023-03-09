package com.june.studyproject.component.view.dispatch

import android.view.MotionEvent

fun MotionEvent.actionName(): String {
    return when (action) {
        MotionEvent.ACTION_DOWN -> "DOWN事件"
        MotionEvent.ACTION_MOVE -> "MOVE事件"
        MotionEvent.ACTION_UP -> "UP事件"
        MotionEvent.ACTION_CANCEL -> "CANCEL事件"
        else -> "${action}-事件"
    }
}