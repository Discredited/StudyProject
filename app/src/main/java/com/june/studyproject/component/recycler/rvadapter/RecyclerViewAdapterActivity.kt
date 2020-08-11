package com.june.studyproject.component.recycler.rvadapter

import android.content.Intent
import com.june.studyproject.R
import com.june.studyproject.base.component.BaseActivity
import com.june.studyproject.base.ext.click
import com.june.studyproject.component.recycler.rvadapter.normal.NormalListActivity
import kotlinx.android.synthetic.main.activity_recycler_view_adapter.*

class RecyclerViewAdapterActivity : BaseActivity() {

    override fun getLayoutResId(): Int = R.layout.activity_recycler_view_adapter

    override fun initView() {
        tvNormalMultiLayout.click {
            startActivity(Intent(this, NormalListActivity::class.java))
        }
    }

    override fun loadData() {
    }
}