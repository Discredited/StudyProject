package com.june.studyproject.expand.loading

import android.content.Context
import android.content.Intent
import com.june.base.basic.ext.click
import com.june.base.basic.part.BaseActivity
import com.june.studyproject.databinding.ActivityLoadingBinding

class LoadingActivity : BaseActivity<ActivityLoadingBinding>() {

    override fun initView() {
        mBinding.tvBegin.click { mBinding.vLoadingView.beginAnimator() }
        mBinding.tvEnd.click { mBinding.vLoadingView.endAnimator() }
    }

    override fun loadData() {
    }

    companion object {
        @JvmStatic
        fun starter(context: Context) {
            context.startActivity(Intent(context, LoadingActivity::class.java))
        }
    }
}