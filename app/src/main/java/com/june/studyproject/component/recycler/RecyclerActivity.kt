package com.june.studyproject.component.recycler

import android.content.Intent
import com.june.studyproject.R
import com.june.studyproject.base.component.BaseActivity
import com.june.studyproject.base.ext.click
import com.june.studyproject.base.ext.initToolbar
import com.june.studyproject.component.recycler.custom.CustomLayoutManagerActivity
import com.june.studyproject.component.recycler.rvadapter.RecyclerViewAdapterActivity
import kotlinx.android.synthetic.main.activity_recycler.*
import kotlinx.android.synthetic.main.view_toolbar_layout.*

class RecyclerActivity : BaseActivity() {

    override fun getLayoutResId(): Int = R.layout.activity_recycler

    override fun initView() {
        toolbar.initToolbar(javaClass.simpleName)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        tvCustomLayoutManager.click {
            startActivity(Intent(this, CustomLayoutManagerActivity::class.java))
        }
        tvItemDecoration.click {
            startActivity(Intent(this, ItemDecorationActivity::class.java))
        }
        tvBaseAdapter.click {
            startActivity(Intent(this, RecyclerViewAdapterActivity::class.java))
        }
    }

    override fun loadData() {
    }
}