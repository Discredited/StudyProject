package com.june.studyproject.expand.image.box

import android.content.Intent
import com.june.studyproject.R
import com.june.studyproject.base.component.BasicActivity
import com.june.studyproject.base.ext.initToolbar
import com.june.studyproject.expand.image.watcher.ImageDragLayoutActivity
import kotlinx.android.synthetic.main.activity_image_box.*
import kotlinx.android.synthetic.main.view_toolbar_layout.*

class ImageBoxActivityBasic : BasicActivity() {

    override fun getLayoutResId(): Int = R.layout.activity_image_box

    override fun initView() {
        toolbar.initToolbar(javaClass.simpleName)

        tvBoxImageView.setOnClickListener {
            startActivity(Intent(this, BoxImageActivityBasic::class.java))
        }
        tvBoxImageLayout.setOnClickListener {
            startActivity(Intent(this, ImageBoxLayoutActivityBasic::class.java))
        }
        tvBoxImageLayoutList.setOnClickListener {
            startActivity(Intent(this, ImageBoxListActivityBasic::class.java))
        }
        tvImageDragLayout.setOnClickListener {
            startActivity(Intent(this, ImageDragLayoutActivity::class.java))
        }
    }

    override fun loadData() {
    }
}