package com.june.base.basic.part

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import timber.log.Timber
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<V : ViewBinding> : Fragment() {

    private var _binding: V? = null
    protected val mBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i("----${javaClass.simpleName}:onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.i("----${javaClass.simpleName}:onCreateView")
        _binding = viewBinding(inflater, container)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.fitsSystemWindows = fitsSystemWindows()
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
     * 通过反射获取ViewBinding
     */
    private fun viewBinding(inflater: LayoutInflater, container: ViewGroup?): V {
        // 获取 Java类的 ParameterizedType
        val parameterizedType = this.javaClass.genericSuperclass as ParameterizedType
        // 通过 ParameterizedType 工具获得泛型具体类型
        val clazz: Class<V> = parameterizedType.actualTypeArguments[0] as Class<V>
        // 获取ViewBinding的inflate方法
        val inflateMethod = clazz.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
        return inflateMethod.invoke(null, inflater, container, false) as V
    }

    /**
     * 初始化View
     */
    protected abstract fun initView()

    protected open fun fitsSystemWindows(): Boolean = true
}