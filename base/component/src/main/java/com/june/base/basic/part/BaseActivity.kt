package com.june.base.basic.part

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import timber.log.Timber
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<V : ViewBinding> : AppCompatActivity() {

    protected lateinit var mBinding: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = viewBinding2()
        setContentView(mBinding.root)
        initView()
        loadData()
    }

    // 通过反射创建ViewBinding失败
    private fun viewBinding2(): V {
        //获取 Java类的 ParameterizedType
        val parameterizedType = this.javaClass.genericSuperclass as ParameterizedType

        //通过 ParameterizedType 工具获得泛型具体类型
        val clazz: Class<V> = parameterizedType.actualTypeArguments[0] as Class<V>

        Timber.e("clazz 的类型：${clazz.simpleName}")

        // 获取ViewBinding的inflate方法
        val inflateMethod = clazz.getMethod("inflate", LayoutInflater::class.java)

        Timber.e("inflateMethod 方法：${inflateMethod.name}")

        return inflateMethod.invoke(null, layoutInflater) as V
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