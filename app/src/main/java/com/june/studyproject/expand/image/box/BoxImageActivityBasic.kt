package com.june.studyproject.expand.image.box

import android.widget.SeekBar
import com.june.imageabout.box.BoxImageView
import com.june.studyproject.R
import com.june.studyproject.base.app.StudyBaseActivity
import com.june.studyproject.base.ext.initToolbar
import com.june.studyproject.base.ext.loadImage
import com.june.studyproject.common.ConstHelper
import com.june.studyproject.databinding.ActivityBoxImageViewBinding

class BoxImageActivityBasic : StudyBaseActivity<ActivityBoxImageViewBinding>() {

    private var mRadiusMax: Int = -1
    private val mSeekBarChangeListener = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
            if (mRadiusMax < 0) {
                mRadiusMax = mBinding.ivBoxImage.width / 2
            }
            val currentRadius = (seekBar.progress * 1F / seekBar.max) * mRadiusMax
            mBinding.ivBoxImage.setCorner(currentRadius)
        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {
        }

        override fun onStopTrackingTouch(seekBar: SeekBar) {
        }
    }

    override fun initView() {
        mBinding.tlLayout.toolbar.apply {
            initToolbar(javaClass.simpleName, R.menu.menu_image_box_corner)
            setNavigationOnClickListener { onBackPressed() }
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.corner_left -> {
                        mBinding.ivBoxImage.setCorner(mBinding.vRadiusSeek.progress.toFloat(), BoxImageView.CORNER_LEFT)
                    }
                    R.id.corner_top -> {
                        mBinding.ivBoxImage.setCorner(mBinding.vRadiusSeek.progress.toFloat(), BoxImageView.CORNER_TOP)
                    }
                    R.id.corner_right -> {
                        mBinding.ivBoxImage.setCorner(mBinding.vRadiusSeek.progress.toFloat(), BoxImageView.CORNER_RIGHT)
                    }
                    R.id.corner_bottom -> {
                        mBinding.ivBoxImage.setCorner(mBinding.vRadiusSeek.progress.toFloat(), BoxImageView.CORNER_BOTTOM)
                    }
                    R.id.corner_diagonal_left -> {
                        mBinding.ivBoxImage.setCorner(
                                mBinding.vRadiusSeek.progress.toFloat(),
                                BoxImageView.CORNER_DIAGONAL_LEFT
                        )
                    }
                    R.id.corner_diagonal_right -> {
                        mBinding.ivBoxImage.setCorner(
                                mBinding.vRadiusSeek.progress.toFloat(),
                                BoxImageView.CORNER_DIAGONAL_RIGHT
                        )
                    }
                    R.id.corner_only_top_left -> {
                        mBinding.ivBoxImage.setCorner(
                                mBinding.vRadiusSeek.progress.toFloat(),
                                BoxImageView.CORNER_ONLY_LEFT_TOP
                        )
                    }
                    R.id.corner_only_top_right -> {
                        mBinding.ivBoxImage.setCorner(
                                mBinding.vRadiusSeek.progress.toFloat(),
                                BoxImageView.CORNER_ONLY_RIGHT_TOP
                        )
                    }
                    R.id.corner_only_bottom_left -> {
                        mBinding.ivBoxImage.setCorner(
                                mBinding.vRadiusSeek.progress.toFloat(),
                                BoxImageView.CORNER_ONLY_LEFT_BOTTOM
                        )
                    }
                    R.id.corner_only_bottom_right -> {
                        mBinding.ivBoxImage.setCorner(
                                mBinding.vRadiusSeek.progress.toFloat(),
                                BoxImageView.CORNER_ONLY_RIGHT_BOTTOM
                        )
                    }
                    else -> {
                        mBinding.ivBoxImage.setCorner(mBinding.vRadiusSeek.progress.toFloat(), BoxImageView.CORNER_ALL)
                    }
                }
                true
            }
        }

        mBinding.vRadiusSeek.setOnSeekBarChangeListener(mSeekBarChangeListener)

        mBinding.ivRandImage.setOnClickListener { mBinding.ivBoxImage.loadImage(ConstHelper.getDiffImage(ConstHelper.IMAGE_SOURCE_GIF)) }
    }

    override fun loadData() {
        mBinding.ivBoxImage.loadImage(ConstHelper.getDiffImage(ConstHelper.IMAGE_SOURCE_GIF))
    }

    override fun onDestroy() {
        mBinding.vRadiusSeek.setOnSeekBarChangeListener(null)
        super.onDestroy()
    }
}