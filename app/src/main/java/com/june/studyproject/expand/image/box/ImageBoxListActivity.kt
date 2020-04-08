package com.june.studyproject.expand.image.box

import androidx.lifecycle.lifecycleScope
import com.june.imageabout.vo.ImageVo
import com.june.imageabout.watcher.ImageWatcherHelper
import com.june.studyproject.R
import com.june.studyproject.base.component.BaseActivity
import com.june.studyproject.base.ext.addLinearItemDecoration
import com.june.studyproject.base.ext.initToolbar
import com.june.studyproject.base.ext.setLinearManager
import com.june.studyproject.common.ConstHelper
import com.june.studyproject.common.Toast
import kotlinx.android.synthetic.main.activity_image_box_list.*
import kotlinx.android.synthetic.main.view_toolbar_layout.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author <a href="mailto:xujun@snqu.com">徐俊</a>
 * @description ImageBox 列表中的表现
 * @version 1.0.0
 * @time 2020/3/30 16:37
 */
class ImageBoxListActivity : BaseActivity() {

    private lateinit var adapter: ImageBoxListAdapter

    override fun getLayoutResId(): Int = R.layout.activity_image_box_list

    override fun initView() {
        toolbar.initToolbar(javaClass.simpleName)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        adapter = ImageBoxListAdapter()
        adapter.setOnItemClickListener { adapter, _, position ->
            val item = adapter.getItem(position) as ImageBoxItemVo
            Toast.showShort(item.title)
        }
        rv_image_box.setLinearManager()
        rv_image_box.adapter = adapter
        rv_image_box.setHasFixedSize(true)
        rv_image_box.addLinearItemDecoration(size = resources.getDimensionPixelSize(R.dimen.dp_15))

        ImageWatcherHelper.instance.setImageLoader(adapter.imageLoader)
    }

    override fun loadData() {
        lifecycleScope.launch {
            val result = withContext(Dispatchers.IO) {
                val list = mutableListOf<ImageBoxItemVo>()
                for (index in 0..30) {
                    val imageList = getList()
                    list.add(ImageBoxItemVo("第 $index 个  共有${imageList.size}张图片", imageList))
                }
                list
            }
            adapter.setNewData(result)
        }
    }

    private fun getList(): MutableList<ImageVo> {
        val random = (Math.random() * 15).toInt()
        val list = mutableListOf<ImageVo>()
        if (random <= 1) {
            val width = 540
            val height = 720
            val diffImage = ConstHelper.getDiffImage(1)
            list.add(ImageVo(diffImage[0], diffImage[0], width, height))
        } else {
            val diffImage = ConstHelper.getDiffImage(random)
            diffImage.forEach {
                list.add(ImageVo(it, it, 0, 0))
            }
        }
        return list
    }
}