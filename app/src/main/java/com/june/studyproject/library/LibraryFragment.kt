package com.june.studyproject.library

import android.os.Bundle
import androidx.core.content.ContextCompat
import com.june.studyproject.R
import com.june.studyproject.base.component.BaseFragment
import com.june.studyproject.base.ext.setLinearManager
import com.june.studyproject.common.LinearItemDecoration
import com.june.studyproject.common.Toast
import com.june.studyproject.component.index.CardExampleVo
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
                ContextCompat.getColor(
                    requireActivity(),
                    R.color.color_theme
                ),
                size = resources.getDimensionPixelSize(R.dimen.dp_5)
            )
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter.setNewData(
            mutableListOf(
                CardExampleVo(
                    "OkHttp + Retrofit",
                    "",
                    { Toast.showShort("OkHttp + Retrofit") },
                    iconRes = R.color.color_style_1_150940
                ),
                CardExampleVo(
                    "RxJava",
                    "",
                    { Toast.showShort("RxJava") },
                    iconRes = R.color.color_style_1_F2E74B
                ),
                CardExampleVo(
                    "Glide",
                    "",
                    { Toast.showShort("Glide") },
                    iconRes = R.color.color_style_1_F2C849
                ),
                CardExampleVo(
                    "BaseQuickAdapter",
                    "",
                    { Toast.showShort("BaseQuickAdapter") },
                    iconRes = R.color.color_style_1_F2B749
                ),
                CardExampleVo(
                    "Video",
                    "",
                    { Toast.showShort("Video") },
                    iconRes = R.color.color_style_1_F29849
                )
            )
        )
    }
}