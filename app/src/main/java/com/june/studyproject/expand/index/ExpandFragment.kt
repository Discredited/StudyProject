package com.june.studyproject.expand.index

import android.content.Intent
import com.june.studyproject.R
import com.june.studyproject.base.component.BaseFragment
import com.june.studyproject.expand.image.box.ImageBoxListActivity
import kotlinx.android.synthetic.main.fragment_expand.*
import java.util.*

class ExpandFragment : BaseFragment() {
    override fun getLayoutResId(): Int = R.layout.fragment_expand

    override fun initView() {
        tv_random.setOnClickListener {
            tv_random.text = Locale.getDefault().country
        }
        tv_start_image_box.setOnClickListener {
            startActivity(Intent(requireActivity(), ImageBoxListActivity::class.java))
        }
    }

    override fun fitsSystemWindows(): Boolean = false
}