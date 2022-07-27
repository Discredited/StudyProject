package com.june.base.basic.part

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatDialog
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class BaseDialog<V : ViewBinding>(
    context: Context,
    theme: Int = 0
) : AppCompatDialog(context, theme) {

    private var _binding: V? = null
    protected val mBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = viewBinding(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        _binding = null
    }

    // 通过反射创建ViewBinding失败
    private fun viewBinding(inflater: LayoutInflater): V {
        // 获取 Java类的 ParameterizedType
        val parameterizedType = this.javaClass.genericSuperclass as ParameterizedType
        // 通过 ParameterizedType 工具获得泛型具体类型
        val clazz: Class<V> = parameterizedType.actualTypeArguments[0] as Class<V>
        // 获取ViewBinding的inflate方法
        val inflateMethod = clazz.getMethod("inflate", LayoutInflater::class.java)
        return inflateMethod.invoke(null, inflater) as V
    }
}