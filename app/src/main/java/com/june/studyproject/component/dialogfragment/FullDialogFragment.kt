package com.june.studyproject.component.dialogfragment

import com.june.base.basic.part.BaseDialogFragment
import com.june.studyproject.databinding.DialogFragmentFullBinding

class FullDialogFragment : BaseDialogFragment<DialogFragmentFullBinding>() {

    override fun initView() {
    }

    companion object {
        fun newInstance(): FullDialogFragment {
            return FullDialogFragment()
        }
    }
}