package com.june.studyproject.expand.img

import androidx.core.content.ContextCompat
import com.june.studyproject.R
import com.june.studyproject.base.component.BaseActivity
import com.june.studyproject.base.ext.initToolbar
import com.june.studyproject.base.ext.setLinearManager
import com.june.studyproject.common.LinearItemDecoration
import kotlinx.android.synthetic.main.activity_image_box_list.*
import kotlinx.android.synthetic.main.view_toolbar_layout.*

class ImageBoxListActivity : BaseActivity() {

    private lateinit var adapter: ImageBoxListAdapter

    override fun getLayoutResId(): Int = R.layout.activity_image_box_list

    override fun initView() {
        toolbar.initToolbar("ImageBoxList")
        toolbar.setNavigationOnClickListener { onBackPressed() }

        adapter = ImageBoxListAdapter()
        rv_image_box.setLinearManager()
        rv_image_box.adapter = adapter
        rv_image_box.setHasFixedSize(true)
        rv_image_box.addItemDecoration(
            LinearItemDecoration(
                ContextCompat.getColor(
                    this,
                    R.color.color_yellow
                ),
                size = resources.getDimensionPixelSize(R.dimen.dp_15)
            )
        )
    }

    override fun loadData() {
        val list = mutableListOf<String>()
        for (index in 0..10) {
            list.add("$index")
        }
        adapter.setNewData(list)
    }
}