package com.june.studyproject.component.recycler.rvadapter

import android.content.Intent
import com.june.studyproject.R
import com.june.studyproject.base.component.BasicActivity
import com.june.studyproject.base.ext.click
import com.june.studyproject.component.recycler.rvadapter.normal.NormalListActivityBasic
import kotlinx.android.synthetic.main.activity_recycler_view_adapter.*

class RecyclerViewAdapterActivityBasic : BasicActivity() {

    override fun getLayoutResId(): Int = R.layout.activity_recycler_view_adapter

    override fun initView() {
        tvNormalMultiLayout.click {
            startActivity(Intent(this, NormalListActivityBasic::class.java))
        }
    }

    override fun loadData() {
    }
}