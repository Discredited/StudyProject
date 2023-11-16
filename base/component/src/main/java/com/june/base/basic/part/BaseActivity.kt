package com.june.base.basic.part

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 * 业务方接入BaseActivity时，
 * 应该自行继承扩展自己的BaseActivity
 * 该基类只提供ViewBinding的基本注入
 */
open class BaseActivity<V : ViewBinding> : AppCompatActivity() {

    protected val mBinding: V by lazy { viewBinding() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
    }

    /**
     * 通过反射创建ViewBinding
     *
     * 特别说明：警告 "Unchecked cast: Type! to Class<V>" 是因为在 Kotlin 中将一个类型强制转换为泛型
     * 类型时，编译器不能在编译时期检查和确认这个转换的安全性。这是因为泛型在 Kotlin（以及 Java）中使用了类
     * 型擦除，意味着在运行时泛型类型的具体信息不可用。
     *
     * 但是我知道这个方法只会用在那些确实拥有合适 inflate 方法的 View Binding 类上，因此我也可以选择简单
     * 地忽略这个警告。
     */
    @Suppress("UNCHECKED_CAST")
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