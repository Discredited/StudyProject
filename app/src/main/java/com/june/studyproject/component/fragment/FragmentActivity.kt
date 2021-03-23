package com.june.studyproject.component.fragment

import com.june.studyproject.base.component.BaseActivity
import com.june.studyproject.base.ext.initToolbar
import com.june.studyproject.component.fragment.lifecycle.FragmentLifecycleActivity
import com.june.studyproject.databinding.ActivityFragmentBinding
import kotlinx.android.synthetic.main.activity_fragment.*
import kotlinx.android.synthetic.main.view_toolbar_layout.*

class FragmentActivity : BaseActivity<ActivityFragmentBinding>() {

    override fun viewBinding(): ActivityFragmentBinding {
        return ActivityFragmentBinding.inflate(layoutInflater)
    }

    override fun initView() {
        toolbar.initToolbar(javaClass.simpleName)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        tvLifecycleFragment.setOnClickListener {
            FragmentLifecycleActivity.starter(this)
        }
    }

    override fun loadData() {
    }
}