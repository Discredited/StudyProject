package com.june.studyproject.library

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.chad.library.adapter.base.animation.SlideInLeftAnimation
import com.june.base.basic.decoration.LinearItemDecoration
import com.june.base.basic.ext.setLinearManager
import com.june.base.basic.part.BaseFragment
import com.june.studyproject.R
import com.june.studyproject.base.ext.initToolbar
import com.june.studyproject.base.ext.itemClick
import com.june.studyproject.common.Toast
import com.june.studyproject.component.index.CardExampleVo
import com.june.studyproject.databinding.FragmentLibraryBinding
import com.june.studyproject.library.okhttp.OkHttpActivity

class LibraryFragment : BaseFragment<FragmentLibraryBinding>() {

    private lateinit var mAdapter: LibraryAdapter

    override fun initView() {
        mBinding.tlLayout.toolbar.initToolbar(getString(R.string.str_library), navIcon = 0, titleCenter = false)

        mAdapter = LibraryAdapter()
        mAdapter.animationEnable = true
        mAdapter.adapterAnimation = SlideInLeftAnimation()
        mAdapter.itemClick { _, _, _ ->
            startActivity(Intent(requireActivity(), OkHttpActivity::class.java))
        }
        mBinding.rvLibrary.apply {
            setLinearManager()
            adapter = mAdapter
            addItemDecoration(
                LinearItemDecoration(
                    ContextCompat.getColor(
                        requireActivity(),
                        R.color.color_transparent
                    ),
                    size = resources.getDimensionPixelSize(R.dimen.dp_5)
                )
            )
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mAdapter.setNewInstance(
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
                ),
                ////////////
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
                ),
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
                ),
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