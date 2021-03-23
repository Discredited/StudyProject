package com.june.studyproject.base.component

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<V : ViewBinding> : AppCompatActivity() {

    protected lateinit var mBinding: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = viewBinding()
        setContentView(mBinding.root)
        initView()
        loadData()
    }

    /**
     * 设置viewBinding
     *
     * @return
     */
    protected abstract fun viewBinding(): V

    /**
     * 初始化View
     */
    protected abstract fun initView()

    /**
     * 加载数据
     */
    protected abstract fun loadData()
}