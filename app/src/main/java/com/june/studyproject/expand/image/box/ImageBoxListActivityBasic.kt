package com.june.studyproject.expand.image.box

import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.ScreenUtils
import com.june.base.basic.ext.addLinearItemDecoration
import com.june.base.basic.ext.setLinearManager
import com.june.studyproject.R
import com.june.base.basic.part.BaseActivity
import com.june.studyproject.base.ext.initToolbar
import com.june.studyproject.common.ConstHelper
import com.june.studyproject.common.Toast
import com.june.studyproject.databinding.ActivityImageBoxLayoutListBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author <a href="mailto:xujun@snqu.com">徐俊</a>
 * @description ImageBoxLayout 列表中的表现
 * @version 1.0.0
 * @time 2020/3/30 16:37
 */
class ImageBoxListActivityBasic : BaseActivity<ActivityImageBoxLayoutListBinding>() {

    private lateinit var adapter: ImageBoxLayoutAdapter

    override fun viewBinding(): ActivityImageBoxLayoutListBinding {
        return ActivityImageBoxLayoutListBinding.inflate(layoutInflater)
    }

    override fun initView() {
        mBinding.tlLayout.toolbar.apply {
            initToolbar(javaClass.simpleName)
            setNavigationOnClickListener { onBackPressed() }
        }

        adapter = ImageBoxLayoutAdapter()
        adapter.setOnItemClickListener { adapter, _, position ->
            val item = adapter.getItem(position) as ImageBoxItemVo
            Toast.showShort(item.title)
        }

        ScreenUtils.getAppScreenWidth()

        mBinding.rvImageBox.apply {
            setLinearManager()
            adapter = adapter
            setHasFixedSize(true)
            addLinearItemDecoration(size = resources.getDimensionPixelSize(R.dimen.dp_15))
        }
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
            adapter.setNewInstance(result)
        }
    }

    private fun getList(): MutableList<MediaVo> {
        val random = (Math.random() * 15).toInt()
        val list = mutableListOf<MediaVo>()
        if (random <= 1) {
            val width = 0
            val height = 0
            val diffImage = ConstHelper.getDiffImage(1)
            list.add(MediaVo(diffImage[0], diffImage[0], width, height))
        } else {
            val diffImage = ConstHelper.getDiffImage(random)
            diffImage.forEach {
                list.add(MediaVo(it, it, 0, 0))
            }
        }
        return list
    }
}