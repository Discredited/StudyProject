package com.june.studyproject.expand.image.box

import android.widget.SeekBar
import com.june.imageabout.box.BoxImageView
import com.june.studyproject.R
import com.june.studyproject.base.component.BaseActivity
import com.june.studyproject.base.ext.initToolbar
import com.june.studyproject.base.ext.loadImage
import com.june.studyproject.common.ConstHelper
import kotlinx.android.synthetic.main.activity_box_image_view.*
import kotlinx.android.synthetic.main.view_toolbar_layout.*
import timber.log.Timber

class BoxImageActivity : BaseActivity() {

    private var mCornerType = BoxImageView.CORNER_ALL

    private val mSeekBarChangeListener = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
            Timber.e("onProgressChanged:${seekBar.progress}")
            val currentRadius = seekBar.progress
            ivBoxImage.setCorner(currentRadius * 1F, mCornerType)
        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {
            Timber.e("onStartTrackingTouch:${seekBar.progress}")
        }

        override fun onStopTrackingTouch(seekBar: SeekBar) {
            Timber.e("onStopTrackingTouch:${seekBar.progress}")
        }

    }

    override fun getLayoutResId(): Int = R.layout.activity_box_image_view

    override fun initView() {
        toolbar.initToolbar(javaClass.simpleName, R.menu.menu_image_box_corner)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.corner_left -> {
                    mCornerType = BoxImageView.CORNER_LEFT
                    ivBoxImage.setCorner(vRadiusSeek.progress.toFloat(), mCornerType)
                }
                R.id.corner_top -> {
                    mCornerType = BoxImageView.CORNER_TOP
                    ivBoxImage.setCorner(vRadiusSeek.progress.toFloat(), mCornerType)
                }
                R.id.corner_right -> {
                    mCornerType = BoxImageView.CORNER_RIGHT
                    ivBoxImage.setCorner(vRadiusSeek.progress.toFloat(), mCornerType)
                }
                R.id.corner_bottom -> {
                    mCornerType = BoxImageView.CORNER_BOTTOM
                    ivBoxImage.setCorner(vRadiusSeek.progress.toFloat(), mCornerType)
                }
            }
            true
        }

        ivBoxImage.loadImage(ConstHelper.getDiffImage())
        vRadiusSeek.setOnSeekBarChangeListener(mSeekBarChangeListener)
    }

    override fun loadData() {
    }

    override fun onDestroy() {
        vRadiusSeek.setOnSeekBarChangeListener(null)
        super.onDestroy()
    }
}