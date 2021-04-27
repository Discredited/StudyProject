package com.june.base.basic.part

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding

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

    protected abstract fun viewBinding(inflater: LayoutInflater, container: ViewGroup?): V

    abstract fun initView()

    open fun layoutParamsWidth() = WindowManager.LayoutParams.MATCH_PARENT

    open fun layoutParamsHeight() = WindowManager.LayoutParams.MATCH_PARENT

    open fun layoutGravity() = Gravity.CENTER
}