package com.june.studyproject.component.index

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.june.studyproject.R
import com.june.base.basic.part.BaseFragment
import com.june.base.basic.decoration.GridItemDecoration
import com.june.base.basic.ext.setGridManager
import com.june.studyproject.base.ext.initToolbar
import com.june.studyproject.common.Toast
import com.june.studyproject.component.activity.index.ExampleActivity
import com.june.studyproject.component.fragment.FragmentActivity
import com.june.studyproject.component.permission.PermissionsActivity
import com.june.studyproject.component.recycler.RecyclerActivity
import com.june.studyproject.component.service.ServiceActivity
import com.june.studyproject.databinding.FragmentComponentBinding

class ComponentFragment : BaseFragment<FragmentComponentBinding>() {

    private lateinit var mAdapter: ComponentAdapter

    override fun viewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentComponentBinding {
        return FragmentComponentBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        mBinding.tlLayout.toolbar.initToolbar(getString(R.string.str_component), navIcon = 0, titleCenter = false)

        mAdapter = ComponentAdapter()
        mBinding.rvComponent.apply {
            setGridManager(2)
            adapter = mAdapter
            setHasFixedSize(true)
            addItemDecoration(GridItemDecoration(2, resources.getDimensionPixelSize(R.dimen.dp_5)))
        }

        mAdapter.setOnItemClickListener { adapter, _, position ->
            (adapter.getItem(position) as CardExampleVo).action()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mAdapter.setNewInstance(
                mutableListOf(
                        CardExampleVo(
                                getString(R.string.str_activity),
                                getString(R.string.activity_desc),
                                { startActivity(Intent(context, ExampleActivity::class.java)) },
                                iconRes = R.color.color_theme
                        ),
                        CardExampleVo(
                                getString(R.string.str_service),
                                getString(R.string.service_desc),
                                { ServiceActivity.start(requireActivity()) },
                                iconRes = R.color.color_purple
                        ),
                        CardExampleVo(
                                getString(R.string.str_broad_cast),
                                getString(R.string.broad_cast_desc),
                                { ServiceActivity.start(requireActivity()) },
                                iconRes = R.color.color_pink
                        ),
                        CardExampleVo(
                                getString(R.string.str_fragment),
                                getString(R.string.fragment_desc),
                                { startActivity(Intent(requireActivity(), FragmentActivity::class.java)) },
                                iconRes = R.color.color_blue
                        ),
                        CardExampleVo(
                                getString(R.string.str_coordinator),
                                getString(R.string.coordinator_desc),
                                { Toast.showShort(R.string.str_coordinator) },
                                iconRes = R.color.color_green
                        ),
                        CardExampleVo(
                                getString(R.string.str_recyclerView),
                                getString(R.string.recycler_desc),
                                { startActivity(Intent(requireActivity(), RecyclerActivity::class.java)) },
                                iconRes = R.color.color_yellow
                        ),
                        CardExampleVo(
                                getString(R.string.str_view_pager_2),
                                getString(R.string.view_pager_2_desc),
                                { Toast.showShort(R.string.str_view_pager_2) },
                                iconRes = R.color.color_red
                        ),
                        CardExampleVo(
                                "Permission",
                                "权限请求",
                                { startActivity(Intent(requireActivity(), PermissionsActivity::class.java)) },
                                iconRes = R.color.color_theme
                        )
                )
        )
    }
}