package com.june.studyproject.component.activity.index

import androidx.recyclerview.widget.LinearLayoutManager
import com.june.studyproject.R
import com.june.studyproject.base.app.StudyBaseActivity
import com.june.studyproject.base.ext.initToolbar
import com.june.studyproject.common.adapter.TitleDescAdapter
import com.june.studyproject.common.vo.TitleDescVo
import com.june.studyproject.component.activity.lifecycle.LifecycleActivity
import com.june.studyproject.databinding.ActivityExampleBinding

/**
 * @author <a href="mailto:xujun@snqu.com">June</a>
 * @description
 * 包含：
 * Activity生命周期
 * Activity启动模式实例
 * Activity调用和启动
 * @version 1.0.0
 * @time 2020/3/30 17:29
 */
class ExampleActivity : StudyBaseActivity<ActivityExampleBinding>() {

    private val mAdapter: TitleDescAdapter = TitleDescAdapter()

    override fun initView() {
        mBinding.tlLayout.toolbar.initToolbar(getString(R.string.str_activity))
        mBinding.tlLayout.toolbar.setNavigationOnClickListener { onBackPressed() }

        mAdapter.setOnItemClickListener { _, _, position ->
            if (position == 0) {
                LifecycleActivity.starter(this)
            }
        }

        mBinding.rvActivity.layoutManager = LinearLayoutManager(this)
        mBinding.rvActivity.adapter = mAdapter
        mBinding.rvActivity.setHasFixedSize(true)
    }

    override fun loadData() {
        mAdapter.setNewInstance(
            mutableListOf(
                TitleDescVo(0, "生命周期过程", "演示 activity 生命周期方法调用顺序以及各种情况下生命周期方法执行情况"),
                TitleDescVo(1, "启动模式", "activity 四种启动模式的说明和演示"),
                TitleDescVo(2, "其他方法和配置说明", "activity 常用方法演示和说明")
            )
        )
    }
}