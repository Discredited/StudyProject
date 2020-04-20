package com.june.studyproject.component.activity

import android.content.Intent
import com.june.studyproject.R
import com.june.studyproject.base.component.BaseActivity
import com.june.studyproject.base.ext.initToolbar
import kotlinx.android.synthetic.main.activity_example.*
import kotlinx.android.synthetic.main.view_toolbar_layout.*

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
class ExampleActivity : BaseActivity() {

    private lateinit var adapter: RecordDisplayAdapter

    override fun getLayoutResId(): Int = R.layout.activity_example

    override fun initView() {
        toolbar.initToolbar(getString(R.string.str_activity))
        toolbar.setNavigationOnClickListener { onBackPressed() }

        toLifecycleActivity.setOnClickListener {
            startActivity(Intent(this, LifecycleActivity::class.java))
        }
    }

    override fun loadData() {
    }
}