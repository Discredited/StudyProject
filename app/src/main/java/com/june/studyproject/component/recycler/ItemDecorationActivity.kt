package com.june.studyproject.component.recycler

import androidx.core.content.ContextCompat
import com.june.base.basic.decoration.GridItemDecoration
import com.june.base.basic.ext.setGridManager
import com.june.studyproject.R
import com.june.studyproject.base.app.StudyBaseActivity
import com.june.studyproject.component.recycler.custom.CustomLayoutManagerAdapter
import com.june.studyproject.databinding.ActivityItemDecorationBinding

class ItemDecorationActivity : StudyBaseActivity<ActivityItemDecorationBinding>() {

    private val adapter = CustomLayoutManagerAdapter()

    override fun initView() {
        mBinding.rvDecoration1.apply {
            setGridManager(3)
            adapter = adapter
            setHasFixedSize(true)
            addItemDecoration(GridItemDecoration(3, 100))
        }

        mBinding.rvDecoration2.apply {
            setGridManager(4)
            adapter = adapter
            setHasFixedSize(true)
            addItemDecoration(GridItemDecoration(4, 100))
        }

        mBinding.rvDecoration3.apply {
            setGridManager(5)
            adapter = adapter
            setHasFixedSize(true)
            addItemDecoration(GridItemDecoration(5, 100))
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