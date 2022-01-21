package com.june.studyproject.component.dialogfragment

import android.view.WindowManager
import com.june.base.basic.part.BaseDialogFragment
import com.june.studyproject.databinding.DialogFragmentWrapBinding

class WrapDialogFragment : BaseDialogFragment<DialogFragmentWrapBinding>() {

    override fun initView() {
    }

    override fun layoutParamsWidth(): Int = WindowManager.LayoutParams.WRAP_CONTENT

    override fun layoutParamsHeight(): Int = WindowManager.LayoutParams.WRAP_CONTENT

    companion object {
        fun newInstance(): WrapDialogFragment {
            return WrapDialogFragment()
        }
    }
}