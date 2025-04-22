package com.june.studyproject.base.app

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.gyf.immersionbar.BarHide
import com.gyf.immersionbar.ImmersionBar
import com.gyf.immersionbar.ktx.immersionBar
import com.june.base.basic.part.BaseActivity

/**
 * Activity基类
 *
 * 2022年02月16日15:27:19
 *
 * @author June
 */
abstract class StudyBaseActivity<V : ViewBinding> : BaseActivity<V>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initStatusBar()
        initView()
        loadData()
    }


    /**
     * 初始化View
     */
    protected abstract fun initView()

    /**
     * 加载数据
     */
    protected abstract fun loadData()

    protected open fun initStatusBar() {
//        immersionBar {
//            statusBarDarkFont(true)
//            hideBar(BarHide.FLAG_HIDE_NAVIGATION_BAR)
//        }
    }
}