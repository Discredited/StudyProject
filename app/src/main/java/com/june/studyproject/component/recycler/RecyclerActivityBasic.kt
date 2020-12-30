package com.june.studyproject.component.recycler

import android.content.Intent
import com.june.studyproject.R
import com.june.studyproject.base.component.BasicActivity
import com.june.studyproject.base.ext.click
import com.june.studyproject.base.ext.initToolbar
import com.june.studyproject.component.recycler.custom.CustomLayoutManagerActivityBasic
import com.june.studyproject.component.recycler.rvadapter.RecyclerViewAdapterActivityBasic
import kotlinx.android.synthetic.main.activity_recycler.*
import kotlinx.android.synthetic.main.view_toolbar_layout.*

class RecyclerActivityBasic : BasicActivity() {

    override fun getLayoutResId(): Int = R.layout.activity_recycler

    override fun initView() {
        toolbar.initToolbar(javaClass.simpleName)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        tvCustomLayoutManager.click {
            startActivity(Intent(this, CustomLayoutManagerActivityBasic::class.java))
        }
        tvItemDecoration.click {
            startActivity(Intent(this, ItemDecorationActivityBasic::class.java))
        }
        tvBaseAdapter.click {
            startActivity(Intent(this, RecyclerViewAdapterActivityBasic::class.java))
        }
    }

    override fun loadData() {
    }
}