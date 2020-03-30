package com.june.studyproject.component.activity

import android.app.Activity
import android.content.Intent
import android.view.KeyEvent
import androidx.core.content.ContextCompat
import com.june.studyproject.R
import com.june.studyproject.base.component.BaseActivity
import com.june.studyproject.base.ext.initToolbar
import com.june.studyproject.base.ext.setLinearManager
import com.june.studyproject.common.LinearItemDecoration
import kotlinx.android.synthetic.main.activity_lifecycle.*
import kotlinx.android.synthetic.main.view_toolbar_layout.*
import timber.log.Timber

/**
 * @author <a href="mailto:xujun@snqu.com">徐俊</a>
 * @description Activity生命周期演示二
 * @version 1.0.0
 * @time 2020/3/30
 */
class LifecycleSecondActivity : BaseActivity() {

    private lateinit var adapter: RecordDisplayAdapter
    private val mRecordList = arrayListOf<RecordDisplayVo>()

    override fun getLayoutResId(): Int = R.layout.activity_lifecycle

    override fun initView() {
        toolbar.initToolbar(javaClass.simpleName)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        adapter = RecordDisplayAdapter(mRecordList)
        rvLifecycle.setLinearManager()
        rvLifecycle.adapter = adapter
        rvLifecycle.setHasFixedSize(true)
        rvLifecycle.addItemDecoration(
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
        intent.getParcelableArrayListExtra<RecordDisplayVo>("RECORD_LIST")?.let {
            mRecordList.addAll(it)
        }
        mRecordList.add(RecordDisplayVo("onCreate()", javaClass.simpleName))
    }

    override fun onStart() {
        super.onStart()
        Timber.e("onStart()")
        mRecordList.add(RecordDisplayVo("onStart()", javaClass.simpleName))
    }

    override fun onRestart() {
        super.onRestart()
        Timber.e("onRestart()")
        mRecordList.add(RecordDisplayVo("onRestart()", javaClass.simpleName))
    }

    override fun onResume() {
        super.onResume()
        Timber.e("onResume()")
        mRecordList.add(RecordDisplayVo("onResume()", javaClass.simpleName))
        adapter.notifyDataSetChanged()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        mRecordList.add(RecordDisplayVo("onKeyDown()", javaClass.simpleName))
        Timber.e("onKeyDown()  $keyCode  ${event?.action}")
        return super.onKeyDown(keyCode, event)
    }

    override fun finish() {
        mRecordList.add(RecordDisplayVo("finish()", javaClass.simpleName))
        val intent = Intent()
        intent.putExtra(RESPONSE_CODE_LIFECYCLE, mRecordList)
        setResult(RESULT_OK, intent)
        super.finish()
        Timber.e("finish()")
    }

    override fun onBackPressed() {
        mRecordList.add(RecordDisplayVo("onBackPressed()", javaClass.simpleName))
        super.onBackPressed()
        Timber.e("onBackPressed()")
    }

    override fun onPause() {
        mRecordList.add(RecordDisplayVo("onPause()", javaClass.simpleName))
        super.onPause()
        Timber.e("onPause()")
    }

    override fun onStop() {
        mRecordList.add(RecordDisplayVo("onStop()", javaClass.simpleName))
        super.onStop()
        Timber.e("onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.e("onDestroy()")
    }

    companion object {
        const val REQUEST_CODE_LIFECYCLE = 100
        const val RESPONSE_CODE_LIFECYCLE = "RESPONSE_CODE_LIFECYCLE"

        fun starter(activity: Activity, list: ArrayList<RecordDisplayVo>) {
            val intent = Intent(activity, LifecycleSecondActivity::class.java)
            intent.putExtra("RECORD_LIST", list)
            activity.startActivityForResult(intent, REQUEST_CODE_LIFECYCLE)
        }
    }
}