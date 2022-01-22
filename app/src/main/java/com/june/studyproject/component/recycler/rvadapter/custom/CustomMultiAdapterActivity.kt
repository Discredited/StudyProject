package com.june.studyproject.component.recycler.rvadapter.custom

import com.june.base.basic.part.BaseActivity
import com.june.rvadapter.BaseAdapter
import com.june.studyproject.databinding.ActivityNormalListBinding

/**
 * 自定义Adapter实现方案，期望它非常简洁，方便使用，扩展性好
 * 使用:
 * XXXXAdapter.Builder()
 *            .putItem(自定义ViewHolder)
 *            .putItem(自定义ViewHolder)
 *            .putItem(自定义ViewHolder)
 *            ......
 *            .build()
 */
class CustomMultiAdapterActivity : BaseActivity<ActivityNormalListBinding>() {

    private val mAdapter = BaseAdapter()

    override fun initView() {

    }

    override fun loadData() {
    }
}