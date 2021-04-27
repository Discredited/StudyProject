package com.june.studyproject.expand.index

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.june.studyproject.R
import com.june.base.basic.part.BaseFragment
import com.june.studyproject.common.ConstHelper
import com.june.studyproject.component.index.CardExampleVo
import com.june.studyproject.component.recycler.custom.CustomLayoutManager
import com.june.studyproject.databinding.FragmentExpandBinding
import com.june.studyproject.expand.image.box.ImageBoxActivity
import timber.log.Timber

class ExpandFragment : BaseFragment<FragmentExpandBinding>() {

    private lateinit var mAdapter: ExpandAdapter

    override fun viewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentExpandBinding {
        return FragmentExpandBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        mAdapter = ExpandAdapter()
        mAdapter.setOnItemClickListener { adapter, _, position ->
            (adapter.getItem(position) as CardExampleVo).action()
        }

        mBinding.rvExpand.apply {
            layoutManager = CustomLayoutManager(requireActivity())
            adapter = mAdapter
            setHasFixedSize(true)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
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
                    val findFirstVisibleItemPosition = customLayoutManager.findFirstVisibleItemPosition()
                    val findLastVisibleItemPosition = customLayoutManager.findLastVisibleItemPosition()
                    Timber.e("onScrolled==>first:$findFirstVisibleItemPosition \nlast:$findLastVisibleItemPosition")
                }
            })
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mAdapter.setNewInstance(mutableListOf(
                CardExampleVo(
                        getString(R.string.str_display_title, "ImageBoxLayout"),
                        "图片九宫格布局，BoxImage演示",
                        { startActivity(Intent(context, ImageBoxActivity::class.java)) },
                        iconUrl = ConstHelper.getDiffImage(ConstHelper.IMAGE_SOURCE_GIF)
                ),
                CardExampleVo(
                        getString(R.string.str_display_title, "ImageBoxLayout2"),
                        "图片九宫格布局，BoxImage演示",
                        { startActivity(Intent(context, ImageBoxActivity::class.java)) },
                        iconUrl = ConstHelper.getDiffImage(ConstHelper.IMAGE_SOURCE_GIF)
                ),
                CardExampleVo(
                        getString(R.string.str_display_title, "ImageBoxLayout3"),
                        "图片九宫格布局，BoxImage演示",
                        { startActivity(Intent(context, ImageBoxActivity::class.java)) },
                        iconUrl = ConstHelper.getDiffImage(ConstHelper.IMAGE_SOURCE_GIF)
                ),
                CardExampleVo(
                        getString(R.string.str_display_title, "ImageBoxLayout4"),
                        "图片九宫格布局，BoxImage演示",
                        { startActivity(Intent(context, ImageBoxActivity::class.java)) },
                        iconUrl = ConstHelper.getDiffImage(ConstHelper.IMAGE_SOURCE_GIF)
                ),
                CardExampleVo(
                        getString(R.string.str_display_title, "ImageBoxLayout5"),
                        "图片九宫格布局，BoxImage演示",
                        { startActivity(Intent(context, ImageBoxActivity::class.java)) },
                        iconUrl = ConstHelper.getDiffImage(ConstHelper.IMAGE_SOURCE_GIF)
                ),
                CardExampleVo(
                        getString(R.string.str_display_title, "ImageBoxLayout6"),
                        "图片九宫格布局，BoxImage演示",
                        { startActivity(Intent(context, ImageBoxActivity::class.java)) },
                        iconUrl = ConstHelper.getDiffImage(ConstHelper.IMAGE_SOURCE_GIF)
                )
        ))
    }

    override fun fitsSystemWindows(): Boolean = false
}