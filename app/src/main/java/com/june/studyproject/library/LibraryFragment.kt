package com.june.studyproject.library

import android.os.Bundle
import androidx.core.content.ContextCompat
import com.june.studyproject.R
import com.june.studyproject.base.component.BaseFragment
import com.june.studyproject.base.ext.setLinearManager
import com.june.studyproject.common.LinearItemDecoration
import kotlinx.android.synthetic.main.fragment_library.*

class LibraryFragment : BaseFragment() {

    private lateinit var adapter: LibraryAdapter

    override fun getLayoutResId(): Int = R.layout.fragment_library

    override fun initView() {
        adapter = LibraryAdapter()

        rv_library.setLinearManager()
        rv_library.adapter = adapter
        rv_library.addItemDecoration(
            LinearItemDecoration(
                ContextCompat.getColor(requireActivity(), R.color.color_theme),
                size = resources.getDimensionPixelSize(R.dimen.dp_5))
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter.setNewData(
            mutableListOf(
                LibraryVo("OkHttp + Retrofit", R.color.color_style_1_150940),
                LibraryVo("RxJava", R.color.color_style_1_F2E74B),
                LibraryVo("Glide", R.color.color_style_1_F2C849),
                LibraryVo("BaseQuickAdapter", R.color.color_style_1_F2B749),
                LibraryVo("Video", R.color.color_style_1_F29849)
            )
        )
    }
}