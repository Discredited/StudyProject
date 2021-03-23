package com.june.studyproject.expand.index

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.june.studyproject.R
import com.june.studyproject.base.component.BaseFragment
import com.june.studyproject.common.ConstHelper
import com.june.studyproject.component.index.CardExampleVo
import com.june.studyproject.component.recycler.custom.CustomLayoutManager
import com.june.studyproject.expand.image.box.ImageBoxActivityBasic
import kotlinx.android.synthetic.main.fragment_expand.*
import timber.log.Timber

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

        rvExpand.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                val state = when (newState) {
                    //RecyclerView当前未滚动
                    RecyclerView.SCROLL_STATE_IDLE -> "停止"
                    //RecyclerView当前正在由外部输入（例如用户触摸输入）拖动
                    RecyclerView.SCROLL_STATE_DRAGGING -> "拖动"
                    //RecyclerView当前处于动画最终状态，而不受外部控制
                    RecyclerView.SCROLL_STATE_SETTLING -> "不受外部控制的最终状态"
                    else -> "unKnow"
                }
                Timber.e("onScrollStateChanged : $state")
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val customLayoutManager = recyclerView.layoutManager as CustomLayoutManager
                var findFirstVisibleItemPosition = customLayoutManager.findFirstVisibleItemPosition()
                var findLastVisibleItemPosition = customLayoutManager.findLastVisibleItemPosition()
                Timber.e("onScrolled==>first:$findFirstVisibleItemPosition \nlast:$findLastVisibleItemPosition")
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter.setNewData(mutableListOf(
            CardExampleVo(
                getString(R.string.str_display_title, "ImageBoxLayout"),
                "图片九宫格布局，BoxImage演示",
                { startActivity(Intent(context, ImageBoxActivityBasic::class.java)) },
                iconUrl = ConstHelper.getDiffImage(ConstHelper.IMAGE_SOURCE_GIF)
            ),
            CardExampleVo(
                getString(R.string.str_display_title, "ImageBoxLayout2"),
                "图片九宫格布局，BoxImage演示",
                { startActivity(Intent(context, ImageBoxActivityBasic::class.java)) },
                iconUrl = ConstHelper.getDiffImage(ConstHelper.IMAGE_SOURCE_GIF)
            ),
            CardExampleVo(
                getString(R.string.str_display_title, "ImageBoxLayout3"),
                "图片九宫格布局，BoxImage演示",
                { startActivity(Intent(context, ImageBoxActivityBasic::class.java)) },
                iconUrl = ConstHelper.getDiffImage(ConstHelper.IMAGE_SOURCE_GIF)
            ),
            CardExampleVo(
                getString(R.string.str_display_title, "ImageBoxLayout4"),
                "图片九宫格布局，BoxImage演示",
                { startActivity(Intent(context, ImageBoxActivityBasic::class.java)) },
                iconUrl = ConstHelper.getDiffImage(ConstHelper.IMAGE_SOURCE_GIF)
            ),
            CardExampleVo(
                getString(R.string.str_display_title, "ImageBoxLayout5"),
                "图片九宫格布局，BoxImage演示",
                { startActivity(Intent(context, ImageBoxActivityBasic::class.java)) },
                iconUrl = ConstHelper.getDiffImage(ConstHelper.IMAGE_SOURCE_GIF)
            ),
            CardExampleVo(
                getString(R.string.str_display_title, "ImageBoxLayout6"),
                "图片九宫格布局，BoxImage演示",
                { startActivity(Intent(context, ImageBoxActivityBasic::class.java)) },
                iconUrl = ConstHelper.getDiffImage(ConstHelper.IMAGE_SOURCE_GIF)
            )
        ))
    }

    override fun fitsSystemWindows(): Boolean = false
}