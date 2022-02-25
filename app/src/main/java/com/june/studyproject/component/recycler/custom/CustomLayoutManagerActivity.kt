package com.june.studyproject.component.recycler.custom

import androidx.core.content.ContextCompat
import com.june.studyproject.R
import com.june.studyproject.base.app.StudyBaseActivity
import com.june.studyproject.base.ext.initToolbar
import com.june.studyproject.databinding.ActivityCustomLayoutManagerBinding


class CustomLayoutManagerActivity : StudyBaseActivity<ActivityCustomLayoutManagerBinding>() {

    private lateinit var adapter: CustomLayoutManagerAdapter

    override fun initView() {
        mBinding.tlLayout.toolbar.initToolbar(javaClass.simpleName)
        mBinding.tlLayout.toolbar.setNavigationOnClickListener { onBackPressed() }

        adapter = CustomLayoutManagerAdapter()

        mBinding.rvCard.apply {
            layoutManager = CustomLayoutManager(this@CustomLayoutManagerActivity)
            adapter = adapter
            setHasFixedSize(true)
        }
    }

    override fun loadData() {
        adapter.setNewInstance(
            mutableListOf(
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
            )
        )
    }
}