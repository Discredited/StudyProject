package com.june.widget.explosion

import android.animation.ValueAnimator
import android.graphics.*
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.Interpolator
import java.util.*
import kotlin.math.pow


/**
 * 爆炸动画
 *
 * 2022/4/27
 * @author June
 */
class ExplosionAnimator(
    private val container: View,
    bitmap: Bitmap,
    private val bound: Rect
) : ValueAnimator() {

    companion object {
        const val DEFAULT_DURATION: Long = 0x400
        val DEFAULT_INTERPOLATOR: Interpolator = AccelerateInterpolator(0.6f)
        const val END_VALUE = 1.4f
        val X: Int = ExplosionUtils.dp2Px(5)
        val Y: Int = ExplosionUtils.dp2Px(20)
        val V: Int = ExplosionUtils.dp2Px(2)
        val W: Int = ExplosionUtils.dp2Px(1)
    }

    private val mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mParticles: MutableList<Particle> = mutableListOf()

    init {
        val random = Random(System.currentTimeMillis())

        val partLen = 15
        val width = bitmap.width / (partLen + 2)
        val height = bitmap.height / (partLen + 2)

        for (i in 0..partLen) {
            for (j in 0..partLen) {
                mParticles.add(
                    generateParticle(
                        bitmap.getPixel((j + 1) * width, (i + 1) * height),
                        random
                    )
                )
            }
        }

        setFloatValues(0F, END_VALUE)
        interpolator = DEFAULT_INTERPOLATOR
        duration = DEFAULT_DURATION
    }

    private fun generateParticle(color: Int, random: Random): Particle {
        val baseRadius = if (random.nextFloat() < 0.2f) {
            V + (X - V) * random.nextFloat()
        } else {
            W + (V - W) * random.nextFloat()
        }

        val nextFloat: Float = random.nextFloat()

        val topTemp = bound.height() * (0.18f * random.nextFloat() + 0.2f)
        val top = if (nextFloat < 0.2f) topTemp else (topTemp + topTemp * 0.2f * random.nextFloat())
        val bottomTemp = bound.height() * (random.nextFloat() - 0.5f) * 1.8f
        val bottom = if (nextFloat < 0.2f) bottomTemp else if (nextFloat < 0.8f) bottomTemp * 0.6f else bottomTemp * 0.3f

        val mag = 4.0f * top / bottom
        val neg = -mag / bottom

        var f = bound.centerX() + Y * (random.nextFloat() - 0.5f)
        val baseCx = f
        val cx = f
        f = bound.centerY() + Y * (random.nextFloat() - 0.5f)
        val baseCy = f
        val cy = f
        val life = END_VALUE / 10 * random.nextFloat()
        val overflow = 0.4f * random.nextFloat()
        val alpha = 1f


        return Particle(
            alpha = alpha,
            color = color,
            cx = cx,
            cy = cy,
            radius = V.toFloat(),
            baseCx = baseCx,
            baseCy = baseCy,
            baseRadius = baseRadius,
            top = top,
            bottom = bottom,
            mag = mag,
            neg = neg,
            life = life,
            overflow = overflow
        )
    }

    fun draw(canvas: Canvas): Boolean {
        if (!isStarted) {
            return false
        }
        for (particle in mParticles) {
            advance(particle, animatedValue as Float)
            if (particle.alpha > 0f) {
                mPaint.color = particle.color
                mPaint.alpha = ((Color.alpha(particle.color) * particle.alpha).toInt())
                canvas.drawCircle(particle.cx, particle.cy, particle.radius, mPaint)
            }
        }
        container.invalidate()
        return true
    }

    override fun start() {
        super.start()
        container.invalidate(bound)
    }

    private fun advance(particle: Particle, factor: Float) {
        var f = 0f
        var normalization = factor / END_VALUE
        if (normalization < particle.life || normalization > 1f - particle.overflow) {
            particle.alpha = 0f
            return
        }
        normalization = (normalization - particle.life) / (1f - particle.life - particle.overflow)
        val f2 = normalization * END_VALUE
        if (normalization >= 0.7f) {
            f = (normalization - 0.7f) / 0.3f
        }
        particle.alpha = 1f - f
        f = particle.bottom * f2
        particle.cx = particle.baseCx + f
        particle.cy = (particle.baseCy - particle.neg * (f.toDouble().pow(2.0).toFloat())) - f * particle.mag
        particle.radius = V + (particle.baseRadius - V) * f2
    }
}