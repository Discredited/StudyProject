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
    }

    /**
     * 通过反射获取ViewBinding
     */
    @Suppress("UNCHECKED_CAST")
    private fun viewBinding(inflater: LayoutInflater, container: ViewGroup?): V {
        // 获取 Java类的 ParameterizedType
        val parameterizedType = this.javaClass.genericSuperclass as ParameterizedType
        // 通过 ParameterizedType 工具获得泛型具体类型
        val clazz: Class<V> = parameterizedType.actualTypeArguments[0] as Class<V>
        // 获取ViewBinding的inflate方法
        val inflateMethod = clazz.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
        return inflateMethod.invoke(null, inflater, container, false) as V
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected open fun fitsSystemWindows(): Boolean = true
}