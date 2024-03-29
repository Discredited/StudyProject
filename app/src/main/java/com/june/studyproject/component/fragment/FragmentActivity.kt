package com.june.studyproject.component.fragment

import com.june.studyproject.base.app.StudyBaseActivity
import com.june.studyproject.base.ext.initToolbar
import com.june.studyproject.component.fragment.lifecycle.FragmentLifecycleActivity
import com.june.studyproject.databinding.ActivityFragmentBinding

class FragmentActivity : StudyBaseActivity<ActivityFragmentBinding>() {

    override fun initView() {
        mBinding.tlLayout.toolbar.initToolbar(javaClass.simpleName)
        mBinding.tlLayout.toolbar.setNavigationOnClickListener { onBackPressed() }

        mBinding.tvLifecycleFragment.setOnClickListener {
            FragmentLifecycleActivity.starter(this)
        }
    }

    override fun loadData() {
    }
}