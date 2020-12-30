package com.june.studyproject.component.fragment

import com.june.studyproject.R
import com.june.studyproject.base.component.BasicActivity
import com.june.studyproject.base.ext.initToolbar
import com.june.studyproject.component.fragment.lifecycle.FragmentLifecycleActivityBasic
import kotlinx.android.synthetic.main.activity_fragment.*
import kotlinx.android.synthetic.main.view_toolbar_layout.*

class FragmentActivityBasic : BasicActivity() {

    override fun getLayoutResId(): Int = R.layout.activity_fragment

    override fun initView() {
        toolbar.initToolbar(javaClass.simpleName)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        tvLifecycleFragment.setOnClickListener {
            FragmentLifecycleActivityBasic.starter(this)
        }
    }

    override fun loadData() {
    }
}