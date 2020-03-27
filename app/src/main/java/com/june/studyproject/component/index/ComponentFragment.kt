package com.june.studyproject.component.index

import android.os.Bundle
import com.june.studyproject.R
import com.june.studyproject.base.component.BaseFragment
import com.june.studyproject.base.ext.initToolbar
import com.june.studyproject.base.ext.setGridManager
import kotlinx.android.synthetic.main.fragment_component.*
import kotlinx.android.synthetic.main.view_toolbar_layout.*

class ComponentFragment : BaseFragment() {

    private lateinit var adapter: ComponentAdapter

    override fun getLayoutResId(): Int = R.layout.fragment_component

    override fun initView() {
        toolbar.initToolbar(getString(R.string.str_component), 0, false)

        adapter = ComponentAdapter()
        rv_component.setGridManager(2)
        rv_component.adapter = adapter
        rv_component.setHasFixedSize(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter.setNewData(mutableListOf(
                CardExampleVo("Activity", "Activity生命周期、启动模式和相关属性参数", iconRes = R.color.color_theme),
                CardExampleVo("Fragment", "Fragment生命周期、切换、属性方法调用", iconRes = R.color.color_green),
                CardExampleVo("RecyclerView", "RecyclerView必知必会", iconRes = R.color.color_yellow),
                CardExampleVo("Coordinator", "关于折叠布局属性设置和Behavior", iconRes = R.color.color_red)
        ))
    }
}