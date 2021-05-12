package com.june.studyproject.expand.loading

import android.content.Context
import android.content.Intent
import com.june.base.basic.part.BaseActivity
import com.june.studyproject.databinding.ActivityLoadingBinding

class LoadingActivity : BaseActivity<ActivityLoadingBinding>() {

    override fun initView() {

    }

    override fun loadData() {
    }

    override fun viewBinding(): ActivityLoadingBinding {
        return ActivityLoadingBinding.inflate(layoutInflater)
    }

    companion object {
        @JvmStatic
        fun starter(context: Context) {
            context.startActivity(Intent(context, LoadingActivity::class.java))
        }
    }
}