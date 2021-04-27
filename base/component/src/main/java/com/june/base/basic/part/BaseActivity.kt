package com.june.base.basic.part

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

    // 通过反射创建ViewBinding失败
    //    private fun viewBinding2(): V {
    //        val parameterizedType = this.javaClass.genericSuperclass as ParameterizedType
    //        val clazz: Class<V> = parameterizedType.actualTypeArguments[0] as Class<V>
    //        val argsClazz: Array<Class<out LayoutInflater>> = arrayOf(layoutInflater.javaClass)
    //        val constructor: Constructor<V> = clazz.getConstructor(*argsClazz)
    //        return constructor.newInstance(*argsClazz)
    //    }

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