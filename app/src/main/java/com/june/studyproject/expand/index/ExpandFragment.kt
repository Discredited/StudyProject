package com.june.studyproject.expand.index

import android.content.Intent
import android.os.Bundle
import com.june.studyproject.R
import com.june.studyproject.base.component.BaseFragment
import com.june.studyproject.common.ConstHelper
import com.june.studyproject.component.index.CardExampleVo
import com.june.studyproject.component.recycler.custom.CustomLayoutManager
import com.june.studyproject.expand.image.box.ImageBoxActivity
import kotlinx.android.synthetic.main.fragment_expand.*

class ExpandFragment : BaseFragment() {

    private lateinit var adapter: ExpandAdapter

    override fun getLayoutResId(): Int = R.layout.fragment_expand

    override fun initView() {
        adapter = ExpandAdapter()
        adapter.setOnItemClickListener { adapter, _, position ->
            (adapter.getItem(position) as CardExampleVo).action()
        }

        rvExpand.layoutManager = CustomLayoutManager(requireActivity())
        rvExpand.adapter = adapter
        rvExpand.setHasFixedSize(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter.setNewData(mutableListOf(
            CardExampleVo(
                getString(R.string.str_display_title, "ImageBoxLayout"),
                "图片九宫格布局，BoxImage演示",
                { startActivity(Intent(context, ImageBoxActivity::class.java)) },
                iconUrl = ConstHelper.getDiffImage(ConstHelper.IMAGE_SOURCE)
            ),
            CardExampleVo(
                getString(R.string.str_display_title, "ImageBoxLayout2"),
                "图片九宫格布局，BoxImage演示",
                { startActivity(Intent(context, ImageBoxActivity::class.java)) },
                iconUrl = ConstHelper.getDiffImage(ConstHelper.IMAGE_SOURCE)
            ),
            CardExampleVo(
                getString(R.string.str_display_title, "ImageBoxLayout3"),
                "图片九宫格布局，BoxImage演示",
                { startActivity(Intent(context, ImageBoxActivity::class.java)) },
                iconUrl = ConstHelper.getDiffImage(ConstHelper.IMAGE_SOURCE)
            ),
            CardExampleVo(
                getString(R.string.str_display_title, "ImageBoxLayout4"),
                "图片九宫格布局，BoxImage演示",
                { startActivity(Intent(context, ImageBoxActivity::class.java)) },
                iconUrl = ConstHelper.getDiffImage(ConstHelper.IMAGE_SOURCE)
            ),
            CardExampleVo(
                getString(R.string.str_display_title, "ImageBoxLayout5"),
                "图片九宫格布局，BoxImage演示",
                { startActivity(Intent(context, ImageBoxActivity::class.java)) },
                iconUrl = ConstHelper.getDiffImage(ConstHelper.IMAGE_SOURCE)
            ),
            CardExampleVo(
                getString(R.string.str_display_title, "ImageBoxLayout6"),
                "图片九宫格布局，BoxImage演示",
                { startActivity(Intent(context, ImageBoxActivity::class.java)) },
                iconUrl = ConstHelper.getDiffImage(ConstHelper.IMAGE_SOURCE)
            )
        ))
    }

    override fun fitsSystemWindows(): Boolean = false
}