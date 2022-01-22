package com.june.studyproject.component.dialogfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import com.june.base.basic.part.BaseDialogFragment
import com.june.studyproject.databinding.DialogFragmentMarginBinding

class MarginDialogFragment : BaseDialogFragment<DialogFragmentMarginBinding>() {

    override fun viewBinding(inflater: LayoutInflater, container: ViewGroup?): DialogFragmentMarginBinding {
        return DialogFragmentMarginBinding.inflate(layoutInflater)
    }

    override fun initView() {
    }

    override fun layoutParamsWidth(): Int = WindowManager.LayoutParams.MATCH_PARENT

    override fun layoutParamsHeight(): Int = WindowManager.LayoutParams.WRAP_CONTENT

    companion object {
        fun newInstance(): MarginDialogFragment {
            return MarginDialogFragment()
        }
    }
}