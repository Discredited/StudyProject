package com.june.studyproject.component.index

import android.os.Bundle
import com.june.studyproject.R
import com.june.studyproject.base.component.BaseFragment
import com.june.studyproject.base.ext.initToolbar
import com.june.studyproject.base.ext.setGridManager
import com.june.studyproject.common.GridItemDecoration
import com.june.studyproject.common.Toast
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
        rv_component.addItemDecoration(
            GridItemDecoration(
                2, resources
                .getDimensionPixelSize(R.dimen.dp_5)
            )
        )

        adapter.setOnItemClickListener { adapter, _, position ->
            (adapter.getItem(position) as CardExampleVo).action()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter.setNewData(
            mutableListOf(
                CardExampleVo(
                    getString(R.string.str_activity),
                    "Activity生命周期、启动模式和相关属性参数",
                    { Toast.showShort(R.string.str_activity) },
                    iconRes = R.color.color_theme
                ),
                CardExampleVo(
                    getString(R.string.str_service),
                    "Service服务生命周期，启动方式以及日常使用场景",
                    { Toast.showShort(R.string.str_service) },
                    iconRes = R.color.color_purple
                ),
                CardExampleVo(
                    getString(R.string.str_fragment),
                    "Fragment生命周期、切换、属性方法调用",
                    { Toast.showShort(R.string.str_fragment) },
                    iconRes = R.color.color_blue),
                CardExampleVo(
                    getString(R.string.str_coordinator),
                    "关于折叠布局属性设置和Behavior",
                    { Toast.showShort(R.string.str_coordinator) },
                    iconRes = R.color.color_yellow
                ),
                CardExampleVo(
                    getString(R.string.str_recyclerView),
                    "RecyclerView必知必会",
                    { Toast.showShort(R.string.str_recyclerView) },
                    iconRes = R.color.color_green
                ),
                CardExampleVo(
                    getString(R.string.str_view_pager_2),
                    "替换ViewPager的组件",
                    { Toast.showShort(R.string.str_view_pager_2) },
                    iconRes = R.color.color_red
                )
            )
        )
    }
}