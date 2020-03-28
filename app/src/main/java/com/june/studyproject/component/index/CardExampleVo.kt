package com.june.studyproject.component.index

class CardExampleVo(
    val title: String,
    val description: String,
    val action: () -> Unit?,
    val iconUrl: String = "",
    val iconRes: Int = 0
)