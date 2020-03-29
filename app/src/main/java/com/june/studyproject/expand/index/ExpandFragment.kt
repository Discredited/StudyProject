package com.june.studyproject.expand.index

import android.content.Intent
import com.june.imageabout.box.ImageVo
import com.june.studyproject.R
import com.june.studyproject.base.component.BaseFragment
import com.june.studyproject.expand.img.ImageBoxListActivity
import com.june.studyproject.expand.img.ImageLoader
import kotlinx.android.synthetic.main.fragment_expand.*

class ExpandFragment : BaseFragment() {
    override fun getLayoutResId(): Int = R.layout.fragment_expand

    override fun initView() {
        v_image_box.setImageLoader(ImageLoader())

        tv_random.setOnClickListener {
            v_image_box.setImageList(getList())
        }
        tv_start_image_box.setOnClickListener {
            startActivity(Intent(requireActivity(), ImageBoxListActivity::class.java))
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
}