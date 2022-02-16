package com.june.studyproject.component.fragment.lifecycle

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.june.studyproject.R
import com.june.studyproject.base.app.StudyBaseActivity
import com.june.studyproject.base.ext.initToolbar
import com.june.studyproject.common.Toast
import com.june.studyproject.component.activity.lifecycle.RecordDisplayVo
import com.june.studyproject.databinding.ActivityFragmentLifecycleBinding
import timber.log.Timber

class FragmentLifecycleActivity : StudyBaseActivity<ActivityFragmentLifecycleBinding>() {

    private val mLifecycleViewModel by viewModels<FragmentLifecycleViewModel>()
    private var mTitleColor: Int = 0
    private var mDescColor: Int = 0

    private var isConfirmExit = false

    override fun onStart() {
        super.onStart()
        Timber.e("onStart()")

        mLifecycleViewModel.mRecordList.add(
            RecordDisplayVo(
                "onStart()",
                javaClass.simpleName,
                mTitleColor,
                mDescColor
            )
        )
    }

    override fun onRestart() {
        super.onRestart()
        Timber.e("onRestart()")

        mLifecycleViewModel.mRecordList.add(
            RecordDisplayVo(
                "onRestart()",
                javaClass.simpleName,
                mTitleColor,
                mDescColor
            )
        )
    }

    override fun onResume() {
        super.onResume()
        Timber.e("onResume()")

        mLifecycleViewModel.mRecordList.add(
            RecordDisplayVo(
                "onResume()",
                javaClass.simpleName,
                mTitleColor,
                mDescColor
            )
        )
    }

    override fun finish() {
        super.finish()
        Timber.e("finish()")
    }

    override fun onBackPressed() {
        Timber.e("onBackPressed()")
        if (isConfirmExit) {
            super.onBackPressed()
        } else {
            isConfirmExit = true

            val list = mutableListOf(
                RecordDisplayVo(
                    "finish()",
                    javaClass.simpleName,
                    mTitleColor,
                    mDescColor
                ),
                RecordDisplayVo(
                    "onBackPressed()",
                    javaClass.simpleName,
                    mTitleColor,
                    mDescColor
                ),
                RecordDisplayVo(
                    "onPause()",
                    "LifecycleFragment"
                ),
                RecordDisplayVo(
                    "onPause()",
                    javaClass.simpleName,
                    mTitleColor,
                    mDescColor
                ),
                RecordDisplayVo(
                    "onStop()",
                    "LifecycleFragment"
                ),
                RecordDisplayVo(
                    "onStop()",
                    javaClass.simpleName,
                    mTitleColor,
                    mDescColor
                ),
                RecordDisplayVo(
                    "onDestroyView()",
                    "LifecycleFragment"
                ),
                RecordDisplayVo(
                    "onDestroy()",
                    "LifecycleFragment"
                ),
                RecordDisplayVo(
                    "onDestroy()",
                    javaClass.simpleName,
                    mTitleColor,
                    mDescColor
                )
            )
            mLifecycleViewModel.mRecordList.addAll(list)
            mLifecycleViewModel.mItemChangeLive.value = true

            Toast.showShort("模拟退出生命周期显示，再按一次退出")
        }
    }

    override fun onPause() {
        super.onPause()
        Timber.e("onPause()")
        mLifecycleViewModel.mRecordList.add(
            RecordDisplayVo(
                "onPause()",
                javaClass.simpleName,
                mTitleColor,
                mDescColor
            )
        )
    }

    override fun onStop() {
        super.onStop()
        Timber.e("onStop()")
        mLifecycleViewModel.mRecordList.add(
            RecordDisplayVo(
                "onStop()",
                javaClass.simpleName,
                mTitleColor,
                mDescColor
            )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.e("onDestroy()")
    }

    override fun initView() {
        mBinding.tlLayout.toolbar.initToolbar(javaClass.simpleName)
        mBinding.tlLayout.toolbar.setNavigationOnClickListener { onBackPressed() }

        mTitleColor = ContextCompat.getColor(this, R.color.color_yellow)
        mDescColor = ContextCompat.getColor(this, R.color.color_yellow_light)
    }

    override fun loadData() {
        Timber.e("onCreate()")

        mLifecycleViewModel.mRecordList.add(
            RecordDisplayVo(
                "onCreate()",
                javaClass.simpleName,
                mTitleColor,
                mDescColor
            )
        )

        supportFragmentManager.beginTransaction()
            .add(
                R.id.fcContainer,
                LifecycleFragment.newInstance(),
                LifecycleFragment::class.java.simpleName
            )
            .commitNow()
    }

    companion object {
        fun starter(context: Context) {
            val intent = Intent(context, FragmentLifecycleActivity::class.java)
            context.startActivity(intent)
        }
    }
}