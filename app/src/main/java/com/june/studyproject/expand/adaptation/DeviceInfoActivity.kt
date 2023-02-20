package com.june.studyproject.expand.adaptation

import android.util.DisplayMetrics
import com.june.studyproject.base.app.StudyBaseActivity
import com.june.studyproject.databinding.ActivityDeviceInfoBinding

/**
 * 设备参数显示
 *
 * 2022/11/29
 * @author June
 */
class DeviceInfoActivity : StudyBaseActivity<ActivityDeviceInfoBinding>() {
    override fun initView() {

    }

    override fun loadData() {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val widthPixels = displayMetrics.widthPixels
        val heightPixels = displayMetrics.heightPixels

        val xdpi = displayMetrics.xdpi
        val ydpi = displayMetrics.ydpi

        val densityDpi = displayMetrics.densityDpi
        val density = displayMetrics.density
        val scaledDensity = displayMetrics.scaledDensity

        val widthDp = widthPixels / density
        val heightDp = heightPixels / density

        val builder = StringBuffer().apply {
            append("widthPixels:${widthPixels}px")
            appendLine()
            append("heightPixels:${heightPixels}px")
            appendLine()
            append("xdpi:${xdpi}dpi")
            appendLine()
            append("ydpi:${xdpi}ydpi")
            appendLine()
            append("densityDpi:${densityDpi}dpi")
            appendLine()
            append("density:${density}")
            appendLine()
            append("scaledDensity:${scaledDensity}")
            appendLine()
            append("widthDp:${widthDp}")
            appendLine()
            append("heightDp:${heightDp}")
        }

        mBinding.tvDeviceInfo.text = builder.toString()
    }
}