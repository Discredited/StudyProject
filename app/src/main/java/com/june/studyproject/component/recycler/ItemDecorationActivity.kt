package com.june.studyproject.component.recycler

import androidx.core.content.ContextCompat
import com.june.studyproject.R
import com.june.studyproject.base.component.BasicActivity
import com.june.studyproject.base.ext.setGridManager
import com.june.studyproject.common.GridItemDecoration
import com.june.studyproject.component.recycler.custom.CustomLayoutManagerAdapter
import kotlinx.android.synthetic.main.activity_item_decoration.*

class ItemDecorationActivity : BasicActivity() {

    private val adapter = CustomLayoutManagerAdapter()

    override fun getLayoutResId(): Int = R.layout.activity_item_decoration

    override fun initView() {
        rvDecoration1.setGridManager(3)
        rvDecoration1.adapter = adapter
        rvDecoration1.setHasFixedSize(true)
        rvDecoration1.addItemDecoration(GridItemDecoration(3, 100))

        rvDecoration2.setGridManager(4)
        rvDecoration2.adapter = adapter
        rvDecoration2.setHasFixedSize(true)
        rvDecoration2.addItemDecoration(GridItemDecoration(4, 100))

        rvDecoration3.setGridManager(5)
        rvDecoration3.adapter = adapter
        rvDecoration3.setHasFixedSize(true)
        rvDecoration3.addItemDecoration(GridItemDecoration(5, 100))
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