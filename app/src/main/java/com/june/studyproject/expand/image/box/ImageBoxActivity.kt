package com.june.studyproject.expand.image.box

import android.content.Intent
import com.june.studyproject.R
import com.june.studyproject.base.component.BaseActivity
import com.june.studyproject.base.ext.initToolbar
import kotlinx.android.synthetic.main.activity_image_box.*
import kotlinx.android.synthetic.main.view_toolbar_layout.*

class ImageBoxActivity : BaseActivity() {

    override fun getLayoutResId(): Int = R.layout.activity_image_box

    override fun initView() {
        toolbar.initToolbar(javaClass.simpleName)

        tvBoxImageView.setOnClickListener {
            startActivity(Intent(this, BoxImageActivity::class.java))
        }
        tvBoxImageList.setOnClickListener {
            startActivity(Intent(this, ImageBoxListActivity::class.java))
        }
    }

    override fun loadData() {
    }
}