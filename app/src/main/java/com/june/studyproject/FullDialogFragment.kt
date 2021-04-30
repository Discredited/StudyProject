package com.june.studyproject

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import com.june.base.basic.ext.click
import com.june.base.basic.part.BaseDialogFragment
import com.june.studyproject.common.Toast
import com.june.studyproject.databinding.DialogFragmentFullBinding

class FullDialogFragment : BaseDialogFragment<DialogFragmentFullBinding>() {

    private var mWidth: Int = WindowManager.LayoutParams.MATCH_PARENT
    private var mHeight: Int = WindowManager.LayoutParams.MATCH_PARENT

    private var mDegree: Float = 0F

    override fun viewBinding(inflater: LayoutInflater, container: ViewGroup?): DialogFragmentFullBinding {
        return DialogFragmentFullBinding.inflate(inflater, container, false)
    }

    override fun layoutParamsWidth(): Int = mWidth

    override fun layoutParamsHeight(): Int = mHeight

    override fun initView() {
        mBinding.tvText.click {
            // 旋转Fragment
            Toast.showShort("旋转屏幕")
            rotationView()
        }
    }

    private fun rotationView() {
        mDegree = if (mDegree == 0F) {
            -90F
        } else {
            0F
        }

        mBinding.root.animate()
                .rotation(mDegree)
                //                .translationX()
                //                .translationY()
                .setDuration(500)
                .start()
    }

    companion object {
        fun newInstance(): FullDialogFragment {
            return FullDialogFragment()
        }
    }
}