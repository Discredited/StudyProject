package com.june.studyproject.base.app

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.gyf.immersionbar.ImmersionBar
import com.june.base.basic.part.BaseActivity

abstract class StudyBaseActivity<V : ViewBinding> : BaseActivity<V>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ImmersionBar.with(this).init()
    }


    /**
     * 初始化View
     */
    protected abstract fun initView()

    /**
     * 加载数据
     */
    protected abstract fun loadData()
}