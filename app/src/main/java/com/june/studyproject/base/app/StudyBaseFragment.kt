package com.june.studyproject.base.app

import android.os.Bundle
import android.view.View
import androidx.viewbinding.ViewBinding
import com.june.base.basic.part.BaseFragment
import timber.log.Timber

/**
 * Fragment基类
 *
 * 2022/2/16
 * @author June
 */
abstract class StudyBaseFragment<V : ViewBinding> : BaseFragment<V>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i("----${javaClass.simpleName}:onCreate")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.i("----${javaClass.simpleName}:onViewCreated")
        initView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Timber.i("----${javaClass.simpleName}:onActivityCreated")
    }

    override fun onStart() {
        super.onStart()
        Timber.i("----${javaClass.simpleName}:onStart")
    }

    override fun onResume() {
        super.onResume()
        Timber.i("----${javaClass.simpleName}:onResume")
    }

    override fun onPause() {
        super.onPause()
        Timber.i("----${javaClass.simpleName}:onPause")
    }

    override fun onStop() {
        super.onStop()
        Timber.i("----${javaClass.simpleName}:onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.i("----${javaClass.simpleName}:onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("----${javaClass.simpleName}:onDestroy")
    }

    /**
     * 初始化View
     */
    protected abstract fun initView()
}