package com.june.base.basic.part

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class BaseDialogFragment<V : ViewBinding> : DialogFragment() {

    private var _binding: V? = null
    protected val mBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = viewBinding(inflater, container)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        super.onActivityCreated(savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(0x00000000))
        dialog?.window?.setGravity(layoutGravity())
        dialog?.window?.setLayout(
            layoutParamsWidth(),
            layoutParamsHeight()
        )
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

    abstract fun initView()

    open fun layoutParamsWidth() = WindowManager.LayoutParams.MATCH_PARENT

    open fun layoutParamsHeight() = WindowManager.LayoutParams.MATCH_PARENT

    open fun layoutGravity() = Gravity.CENTER
}