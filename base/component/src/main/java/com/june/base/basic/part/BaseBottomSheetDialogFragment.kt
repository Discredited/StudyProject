package com.june.base.basic.part

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetDialogFragment<V : ViewBinding> : BottomSheetDialogFragment() {

    private var _binding: V? = null
    protected val mBinding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = viewBinding(inflater, container)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    /**
     * 设置viewBinding
     *
     * @return
     */
    abstract fun viewBinding(inflater: LayoutInflater, container: ViewGroup?): V

    /**
     * 初始化View
     */
    protected abstract fun initView()
}