package com.june.studyproject.component.activity

import java.util.*

class RecordDisplayVo(
        val title: String,
        val desc: String,
        timeInMillis: Long = System.currentTimeMillis()
) {

    val hour: String
    val minute: String
    val second: String
    val millis: String

    init {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeInMillis
        hour = calendar.get(Calendar.HOUR_OF_DAY).toString()
        minute = calendar.get(Calendar.MINUTE).toString()
        second = calendar.get(Calendar.SECOND).toString()
        millis = calendar.get(Calendar.MILLISECOND).toString()
    }
}