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


    private var mRadiusMax: Int = -1
    private val mSeekBarChangeListener = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
            Timber.e("onProgressChanged:${seekBar.progress}")
            if (mRadiusMax < 0) {
                mRadiusMax = ivBoxImage.width / 2
            }
            val currentRadius = (seekBar.progress * 1F / seekBar.max) * mRadiusMax
            ivBoxImage.setCorner(currentRadius)
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
                    ivBoxImage.setCorner(vRadiusSeek.progress.toFloat(), BoxImageView.CORNER_LEFT)
                }
                R.id.corner_top -> {
                    ivBoxImage.setCorner(vRadiusSeek.progress.toFloat(), BoxImageView.CORNER_TOP)
                }
                R.id.corner_right -> {
                    ivBoxImage.setCorner(vRadiusSeek.progress.toFloat(), BoxImageView.CORNER_RIGHT)
                }
                R.id.corner_bottom -> {
                    ivBoxImage.setCorner(vRadiusSeek.progress.toFloat(), BoxImageView.CORNER_BOTTOM)
                }
                R.id.corner_diagonal_left -> {
                    ivBoxImage.setCorner(
                        vRadiusSeek.progress.toFloat(),
                        BoxImageView.CORNER_DIAGONAL_LEFT
                    )
                }
                R.id.corner_diagonal_right -> {
                    ivBoxImage.setCorner(
                        vRadiusSeek.progress.toFloat(),
                        BoxImageView.CORNER_DIAGONAL_RIGHT
                    )
                }
                R.id.corner_only_top_left -> {
                    ivBoxImage.setCorner(
                        vRadiusSeek.progress.toFloat(),
                        BoxImageView.CORNER_ONLY_LEFT_TOP
                    )
                }
                R.id.corner_only_top_right -> {
                    ivBoxImage.setCorner(
                        vRadiusSeek.progress.toFloat(),
                        BoxImageView.CORNER_ONLY_RIGHT_TOP
                    )
                }
                R.id.corner_only_bottom_left -> {
                    ivBoxImage.setCorner(
                        vRadiusSeek.progress.toFloat(),
                        BoxImageView.CORNER_ONLY_LEFT_BOTTOM
                    )
                }
                R.id.corner_only_bottom_right -> {
                    ivBoxImage.setCorner(
                        vRadiusSeek.progress.toFloat(),
                        BoxImageView.CORNER_ONLY_RIGHT_BOTTOM
                    )
                }
                else -> {
                    ivBoxImage.setCorner(vRadiusSeek.progress.toFloat(), BoxImageView.CORNER_ALL)
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