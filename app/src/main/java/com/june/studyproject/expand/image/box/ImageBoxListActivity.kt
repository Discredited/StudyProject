package com.june.studyproject.expand.image.box

import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.june.imageabout.box.ImageVo
import com.june.studyproject.R
import com.june.studyproject.base.component.BaseActivity
import com.june.studyproject.base.ext.initToolbar
import com.june.studyproject.base.ext.setLinearManager
import com.june.studyproject.common.LinearItemDecoration
import kotlinx.android.synthetic.main.activity_image_box_list.*
import kotlinx.android.synthetic.main.view_toolbar_layout.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
        lifecycleScope.launch {
            val result = withContext(Dispatchers.IO) {
                val list = mutableListOf<ImageBoxItemVo>()
                for (index in 0..10) {
                    val imageList = getList()
                    list.add(ImageBoxItemVo("第 $index 个  共有${imageList.size}张图片", imageList))
                }
                list
            }
            adapter.setNewData(result)
        }
    }


    private fun getList(): MutableList<ImageVo> {
        val random = (Math.random() * 10).toInt()
        val list = mutableListOf<ImageVo>()
        if (random <= 1) {
            val width = 540
            val height = 720
            list.add(ImageVo("11111", width, height))
        } else {
            for (index in 0..random) {
                list.add(ImageVo("11111", 0, 0))
            }
        }
        return list
    }
}