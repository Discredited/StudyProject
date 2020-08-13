package com.june.studyproject.library

import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.june.studyproject.R
import com.june.studyproject.base.component.BaseFragment
import com.june.studyproject.base.ext.initToolbar
import com.june.studyproject.base.ext.itemClick
import com.june.studyproject.base.ext.setLinearManager
import com.june.studyproject.common.LinearItemDecoration
import com.june.studyproject.common.Toast
import com.june.studyproject.component.index.CardExampleVo
import com.june.studyproject.library.okhttp.OkHttpActivity
import kotlinx.android.synthetic.main.fragment_library.*
import kotlinx.android.synthetic.main.view_toolbar_layout.*

class LibraryFragment : BaseFragment() {

    private lateinit var adapter: LibraryAdapter

    override fun getLayoutResId(): Int = R.layout.fragment_library

    override fun initView() {
        toolbar.initToolbar(getString(R.string.str_library), navIcon = 0, titleCenter = false)

        adapter = LibraryAdapter()
        adapter.itemClick { _, _, _ ->
            startActivity(Intent(requireActivity(), OkHttpActivity::class.java))
        }
        rv_library.setLinearManager()
        rv_library.adapter = adapter
        rv_library.addItemDecoration(
            LinearItemDecoration(
                ContextCompat.getColor(
                    requireActivity(),
                    R.color.color_transparent
                ),
                size = resources.getDimensionPixelSize(R.dimen.dp_5)
            )
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter.setNewInstance(
            mutableListOf(
                CardExampleVo(
                    "OkHttp + Retrofit",
                    "",
                    { Toast.showShort("OkHttp + Retrofit") },
                    iconRes = R.color.color_style_1_1
                ),
                CardExampleVo(
                    "RxJava",
                    "",
                    { Toast.showShort("RxJava") },
                    iconRes = R.color.color_style_1_2
                ),
                CardExampleVo(
                    "Glide",
                    "",
                    { Toast.showShort("Glide") },
                    iconRes = R.color.color_style_1_3
                ),
                CardExampleVo(
                    "BaseQuickAdapter",
                    "",
                    { Toast.showShort("BaseQuickAdapter") },
                    iconRes = R.color.color_style_1_4
                ),
                CardExampleVo(
                    "Video",
                    "",
                    { Toast.showShort("Video") },
                    iconRes = R.color.color_style_1_5
                )
            )
        )
    }
}