package com.june.studyproject.component.activity.lifecycle

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.KeyEvent
import androidx.core.content.ContextCompat
import com.june.studyproject.R
import com.june.studyproject.base.component.BaseActivity
import com.june.studyproject.base.ext.initToolbar
import com.june.studyproject.base.ext.setLinearManager
import com.june.studyproject.common.LinearItemDecoration
import com.june.studyproject.databinding.ActivityLifecycleBinding
import timber.log.Timber

class LifecycleActivity : BaseActivity<ActivityLifecycleBinding>() {

    private lateinit var adapter: RecordDisplayAdapter
    private val mRecordList = arrayListOf<RecordDisplayVo>()

    private var isFromResult = false
    private var isToNext = false

    private var mTitleColor = 0
    private var mDescColor = 0

    override fun viewBinding(): ActivityLifecycleBinding = ActivityLifecycleBinding.inflate(layoutInflater)

    override fun initView() {
        mBinding.tlLayout.toolbar.initToolbar(javaClass.simpleName, R.menu.menu_text_next)
        mBinding.tlLayout.toolbar.setNavigationOnClickListener { onBackPressed() }
        mBinding.tlLayout.toolbar.setOnMenuItemClickListener {
            //跳转其他页面之前,当前当前页面会先执行onPause
            //具体参照logo日志
            isToNext = true
            mRecordList.add(RecordDisplayVo("onPause()", javaClass.simpleName))
            LifecycleSecondActivity.starter(this, mRecordList)
            true
        }

        adapter = RecordDisplayAdapter(mRecordList)
        mBinding.rvLifecycle.setLinearManager()
        mBinding.rvLifecycle.adapter = adapter
        mBinding.rvLifecycle.setHasFixedSize(true)
        mBinding.rvLifecycle.addItemDecoration(
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

        Timber.e("onCreate()")
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
        if (isFromResult) {
            mRecordList.add(RecordDisplayVo("onStop()", "LifecycleSecondActivity", mTitleColor, mDescColor))
            mRecordList.add(RecordDisplayVo("onDestroy()", "LifecycleSecondActivity", mTitleColor, mDescColor))
            isFromResult = false
        }
        adapter.notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Timber.e("onActivityResult()")
        if (resultCode != Activity.RESULT_OK) {
            return
        }
        if (requestCode == LifecycleSecondActivity.REQUEST_CODE_LIFECYCLE) {
            isFromResult = true
            isToNext = false
            data?.getParcelableArrayListExtra<RecordDisplayVo>(LifecycleSecondActivity.RESPONSE_CODE_LIFECYCLE)?.let {
                mRecordList.clear()
                mRecordList.addAll(it)
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        mRecordList.add(RecordDisplayVo("onKeyDown()", javaClass.simpleName))
        Timber.e("onKeyDown()  $keyCode  ${event?.action}")
        return super.onKeyDown(keyCode, event)
    }

    override fun finish() {
        mRecordList.add(RecordDisplayVo("finish()", javaClass.simpleName))
        super.finish()
        Timber.e("finish()")
    }

    override fun onBackPressed() {
        mRecordList.add(RecordDisplayVo("onBackPressed()", javaClass.simpleName))
        super.onBackPressed()
        Timber.e("onBackPressed()")
    }

    override fun onPause() {
        if (!isToNext) {
            mRecordList.add(RecordDisplayVo("onPause()", javaClass.simpleName))
        }
        super.onPause()
        Timber.e("onPause()")
    }

    override fun onStop() {
        if (!isToNext) {
            mRecordList.add(RecordDisplayVo("onStop()", javaClass.simpleName))
        }
        super.onStop()
        Timber.e("onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.e("onDestroy()")
    }

    companion object {
        fun starter(context: Context) {
            context.startActivity(Intent(context, LifecycleActivity::class.java))
        }
    }
}