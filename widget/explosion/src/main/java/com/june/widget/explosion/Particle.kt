package com.june.widget.explosion

data class Particle(
    var alpha: Float,
    val color: Int,
    var cx: Float,
    var cy: Float,
    var radius: Float,
    val baseCx: Float,
    val baseCy: Float,
    val baseRadius: Float,
    val top: Float,
    val bottom: Float,
    val mag: Float,
    val neg: Float,
    val life: Float,
    val overflow: Float
)