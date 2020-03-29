package com.june.studyproject.expand

import androidx.appcompat.widget.AppCompatImageView
import com.june.imageabout.box.ImageBoxLoader
import com.june.imageabout.box.ImageVo
import com.june.studyproject.R
import com.june.studyproject.base.component.BaseFragment
import com.june.studyproject.base.ext.loadRoundImage
import com.june.studyproject.common.ConstHelper
import kotlinx.android.synthetic.main.fragment_expand.*

class ExpandFragment : BaseFragment() {
    override fun getLayoutResId(): Int = R.layout.fragment_expand

    override fun initView() {
        v_image_box.setImageLoader(ImageLoader())

        tv_random.setOnClickListener {
            v_image_box.setImageList(getList())
        }
    }

    private fun getList(): MutableList<ImageVo> {
        val random = (Math.random() * 10).toInt()
        val list = mutableListOf<ImageVo>()
        if (random <= 1) {
            val width = resources.getDimensionPixelSize(R.dimen.dp_240)
            val height = resources.getDimensionPixelSize(R.dimen.dp_320)
            list.add(ImageVo("11111", width, height))
        } else {
            for (index in 0..random) {
                list.add(ImageVo("11111", 0, 0))
            }
        }
        return list
    }

    override fun fitsSystemWindows(): Boolean = false

    class ImageLoader : ImageBoxLoader {
        override fun loadImage(imageView: AppCompatImageView, image: ImageVo, position: Int) {
            imageView.loadRoundImage(ConstHelper.IMAGE_SOURCE[position])
        }
    }
}