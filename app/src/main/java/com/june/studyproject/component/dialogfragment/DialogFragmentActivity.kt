package com.june.studyproject.component.dialogfragment

import android.content.Context
import android.content.Intent
import com.june.base.basic.ext.click
import com.june.style.R
import com.june.studyproject.base.app.StudyBaseActivity
import com.june.studyproject.base.ext.initToolbar
import com.june.studyproject.databinding.ActivityDialogFragmentBinding

class DialogFragmentActivity : StudyBaseActivity<ActivityDialogFragmentBinding>() {

    override fun initView() {
        mBinding.tlLayout.toolbar.initToolbar(getString(R.string.str_dialog_fragment))
        mBinding.btDialogFragmentFull.click {
            FullDialogFragment.newInstance().show(supportFragmentManager, "FullDialogFragment")
        }
        mBinding.btDialogFragmentWrap.click {
            WrapDialogFragment.newInstance().show(supportFragmentManager, "WrapDialogFragment")
        }
        mBinding.btDialogFragmentMargin.click {
            MarginDialogFragment.newInstance().show(supportFragmentManager, "MarginDialogFragment")
        }
    }

    override fun loadData() {
    }

    companion object {
        fun starter(context: Context) {
            context.startActivity(Intent(context, DialogFragmentActivity::class.java))
        }
    }
}