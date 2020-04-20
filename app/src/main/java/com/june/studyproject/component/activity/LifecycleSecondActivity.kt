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
 * @author <a href="mailto:xujun@snqu.com">June</a>
 * @description Activity生命周期演示二
 * @version 1.0.0
 * @time 2020/3/30
 */
class LifecycleSecondActivity : BaseActivity() {

    private lateinit var adapter: RecordDisplayAdapter
    private val mRecordList = arrayListOf<RecordDisplayVo>()

    private var mTitleColor = 0
    private var mDescColor = 0
    private var isFirstResume = true

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
        mTitleColor = ContextCompat.getColor(this, R.color.color_yellow)
        mDescColor = ContextCompat.getColor(this, R.color.color_yellow_light)

        intent.getParcelableArrayListExtra<RecordDisplayVo>("RECORD_LIST")?.let {
            mRecordList.addAll(it)
        }
        Timber.e("onStart()")
        mRecordList.add(RecordDisplayVo("onCreate()", javaClass.simpleName, mTitleColor, mDescColor))
    }

    override fun onStart() {
        super.onStart()
        Timber.e("onStart()")
        mRecordList.add(RecordDisplayVo("onStart()", javaClass.simpleName, mTitleColor, mDescColor))
    }

    override fun onRestart() {
        super.onRestart()
        Timber.e("onRestart()")
        mRecordList.add(RecordDisplayVo("onRestart()", javaClass.simpleName, mTitleColor, mDescColor))
    }

    override fun onResume() {
        super.onResume()
        Timber.e("onResume()")
        mRecordList.add(RecordDisplayVo("onResume()", javaClass.simpleName, mTitleColor, mDescColor))
        if (isFirstResume) {
            //当前页面创建、开始、恢复之后，上一个页面才会调用stop方法
            mRecordList.add(RecordDisplayVo("onStop()", "LifecycleActivity"))
            isFirstResume = false
        }
        adapter.notifyDataSetChanged()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        mRecordList.add(RecordDisplayVo("onKeyDown()", javaClass.simpleName, mTitleColor, mDescColor))
        Timber.e("onKeyDown()  $keyCode  ${event?.action}")
        return super.onKeyDown(keyCode, event)
    }

    override fun finish() {
        //setResult应该在finish或者onBackPressed之前设置
        //onPause onStop onDestroy之前就会返回result结果默认RESULT_CANCELED
        mRecordList.add(RecordDisplayVo("finish()", javaClass.simpleName, mTitleColor, mDescColor))
        mRecordList.add(RecordDisplayVo("onBackPressed()", javaClass.simpleName, mTitleColor, mDescColor))
        mRecordList.add(RecordDisplayVo("onPause()", javaClass.simpleName, mTitleColor, mDescColor))
        //stop和onDestroy方法会在上一个页面onResume以后调用
        val intent = Intent()
        intent.putExtra(RESPONSE_CODE_LIFECYCLE, mRecordList)
        setResult(RESULT_OK, intent)

        super.finish()
        Timber.e("finish()")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Timber.e("onBackPressed()")
    }

    override fun onPause() {
        super.onPause()
        Timber.e("onPause()")
        if (!isFinishing) {
            mRecordList.add(RecordDisplayVo("onPause()", javaClass.simpleName, mTitleColor, mDescColor))
        }
    }

    override fun onStop() {
        super.onStop()
        Timber.e("onStop()")
        if (!isFinishing) {
            mRecordList.add(RecordDisplayVo("onStop()", javaClass.simpleName, mTitleColor, mDescColor))
        }
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