package com.june.studyproject.component.activity

import androidx.core.content.ContextCompat
import com.june.studyproject.R
import com.june.studyproject.base.component.BaseActivity
import com.june.studyproject.base.ext.initToolbar
import com.june.studyproject.base.ext.setLinearManager
import com.june.studyproject.common.LinearItemDecoration
import kotlinx.android.synthetic.main.activity_example.*
import kotlinx.android.synthetic.main.view_toolbar_layout.*

/**
 * @author <a href="mailto:xujun@snqu.com">徐俊</a>
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
    private val mRecordList = mutableListOf<RecordDisplayVo>()

    override fun getLayoutResId(): Int = R.layout.activity_example

    override fun initView() {
        toolbar.initToolbar(getString(R.string.str_activity))
        toolbar.setNavigationOnClickListener { onBackPressed() }

        adapter = RecordDisplayAdapter()
        rvRecordDisplay.setLinearManager()
        rvRecordDisplay.adapter = adapter
        rvRecordDisplay.setHasFixedSize(true)
        rvRecordDisplay.addItemDecoration(
                LinearItemDecoration(
                        ContextCompat.getColor(
                                this,
                                R.color.color_transparent
                        ),
                        size = resources.getDimensionPixelSize(R.dimen.dp_10)
                )
        )
    }

    override fun loadData() {
        mRecordList.add(RecordDisplayVo("onCreate()", javaClass.simpleName))
    }

    override fun onStart() {
        super.onStart()
        mRecordList.add(RecordDisplayVo("onStart()", javaClass.simpleName))
    }

    override fun onResume() {
        super.onResume()
        mRecordList.add(RecordDisplayVo("onResume()", javaClass.simpleName))
        adapter.setNewData(mRecordList)
    }

    override fun onPause() {
        super.onPause()
        mRecordList.add(RecordDisplayVo("onPause()", javaClass.simpleName))
    }

    override fun onStop() {
        super.onStop()
        mRecordList.add(RecordDisplayVo("onStop()", javaClass.simpleName))
    }

    override fun onDestroy() {
        super.onDestroy()
        mRecordList.add(RecordDisplayVo("onDestroy()", javaClass.simpleName))
    }
}