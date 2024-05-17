package com.june.studyproject.component.recycler.rvadapter.custom

import com.june.studyproject.base.app.StudyBaseActivity
import com.june.studyproject.databinding.ActivityNormalListBinding

/**
 * 自定义Adapter实现方案，期望它非常简洁，方便使用，扩展性好
 * 使用:
 * XXXXAdapter.Builder()
 *            .putItem(自定义Creator(ViewHolder))
 *            .putItem(自定义Creator(ViewHolder))
 *            .putItem(自定义Creator(ViewHolder))
 *            ......
 *            .build()
 */
class CustomMultiAdapterActivity : StudyBaseActivity<ActivityNormalListBinding>() {

    override fun initView() {

    }

    override fun loadData() {
    }
}