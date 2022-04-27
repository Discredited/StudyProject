package com.june.studyproject.component.recycler.custom

import androidx.core.content.ContextCompat
import com.june.studyproject.R
import com.june.studyproject.base.app.StudyBaseActivity
import com.june.studyproject.base.ext.initToolbar
import com.june.studyproject.databinding.ActivityCustomLayoutManagerBinding


/**
 * 自定义LayoutManager
 *
 * 2022年04月27日15:27:57
 *
 * @author:June
 */
class CustomLayoutManagerActivity : StudyBaseActivity<ActivityCustomLayoutManagerBinding>() {

    private val mAdapter by lazy { CustomLayoutManagerAdapter() }

    override fun initView() {
        mBinding.tlLayout.toolbar.initToolbar(javaClass.simpleName)
        mBinding.tlLayout.toolbar.setNavigationOnClickListener { onBackPressed() }

        mBinding.rvCard.apply {
            layoutManager = CustomLayoutManager(this@CustomLayoutManagerActivity)
            adapter = mAdapter
            setHasFixedSize(true)
        }
    }

    override fun loadData() {
        mAdapter.setNewInstance(
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