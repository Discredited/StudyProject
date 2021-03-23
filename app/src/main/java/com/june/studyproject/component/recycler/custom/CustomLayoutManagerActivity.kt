package com.june.studyproject.component.recycler.custom

import androidx.core.content.ContextCompat
import com.june.studyproject.R
import com.june.studyproject.base.component.BasicActivity
import com.june.studyproject.base.ext.initToolbar
import kotlinx.android.synthetic.main.activity_custom_layout_manager.*
import kotlinx.android.synthetic.main.view_toolbar_layout.*


class CustomLayoutManagerActivity : BasicActivity() {

    private lateinit var adapter: CustomLayoutManagerAdapter

    override fun getLayoutResId(): Int = R.layout.activity_custom_layout_manager

    override fun initView() {
        toolbar.initToolbar(javaClass.simpleName)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        adapter = CustomLayoutManagerAdapter()

        rvCard.layoutManager = CustomLayoutManager(this)
        rvCard.adapter = adapter
        rvCard.setHasFixedSize(true)
    }

    override fun loadData() {
        adapter.setNewData(mutableListOf(
            ContextCompat.getColor(this, R.color.color_style_1_1),
            ContextCompat.getColor(this, R.color.color_style_1_2),
            ContextCompat.getColor(this, R.color.color_style_1_3),
            ContextCompat.getColor(this, R.color.color_style_1_4),
            ContextCompat.getColor(this, R.color.color_style_1_5),

            ContextCompat.getColor(this, R.color.color_style_1_1),
            ContextCompat.getColor(this, R.color.color_style_1_2),
            ContextCompat.getColor(this, R.color.color_style_1_3),
            ContextCompat.getColor(this, R.color.color_style_1_4),
            ContextCompat.getColor(this, R.color.color_style_1_5)
        ))
    }
}