package com.june.studyproject.component.fragment

import com.june.studyproject.R
import com.june.studyproject.base.component.BaseActivity
import com.june.studyproject.base.ext.initToolbar
import com.june.studyproject.component.fragment.lifecycle.FragmentLifecycleActivity
import kotlinx.android.synthetic.main.activity_fragment.*
import kotlinx.android.synthetic.main.view_toolbar_layout.*

class FragmentActivity : BaseActivity() {

    override fun getLayoutResId(): Int = R.layout.activity_fragment

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