package com.june.studyproject.component.dialogfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.june.base.basic.part.BaseDialogFragment
import com.june.studyproject.databinding.DialogFragmentFullBinding

class FullDialogFragment : BaseDialogFragment<DialogFragmentFullBinding>() {

    override fun viewBinding(inflater: LayoutInflater, container: ViewGroup?): DialogFragmentFullBinding {
        return DialogFragmentFullBinding.inflate(layoutInflater, container, false)
    }

    override fun initView() {
    }

    companion object {
        fun newInstance(): FullDialogFragment {
            return FullDialogFragment()
        }
    }
}