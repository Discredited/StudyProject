package com.june.base.basic.part

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 * 业务方接入BaseActivity时，
 * 应该自行继承扩展自己的BaseActivity
 *
 * 改基类只提供ViewBinding的基本注入
 */
open class BaseActivity<V : ViewBinding> : AppCompatActivity() {

    protected val mBinding: V by lazy { viewBinding() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
    }

    // 通过反射创建ViewBinding
    private fun viewBinding(): V {
        // 获取 Java类的 ParameterizedType
        val parameterizedType = this.javaClass.genericSuperclass as ParameterizedType
        // 通过 ParameterizedType 工具获得泛型具体类型
        val clazz: Class<V> = parameterizedType.actualTypeArguments[0] as Class<V>
        // 获取ViewBinding的inflate方法
        val inflateMethod = clazz.getMethod("inflate", LayoutInflater::class.java)
        return inflateMethod.invoke(null, layoutInflater) as V
    }
}