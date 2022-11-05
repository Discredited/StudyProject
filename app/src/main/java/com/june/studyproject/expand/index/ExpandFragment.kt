package com.june.studyproject.expand.index

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.june.base.basic.ext.click
import com.june.function.pdfpreview.PDFPreViewActivity
import com.june.studyproject.R
import com.june.studyproject.base.app.StudyBaseFragment
import com.june.studyproject.common.ConstHelper
import com.june.studyproject.component.index.CardExampleVo
import com.june.studyproject.component.recycler.custom.CustomLayoutManager
import com.june.studyproject.databinding.FragmentExpandBinding
import com.june.studyproject.expand.ExplosionActivity
import com.june.studyproject.expand.image.box.ImageBoxActivity
import timber.log.Timber

class ExpandFragment : StudyBaseFragment<FragmentExpandBinding>() {

    private lateinit var mAdapter: ExpandAdapter

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

        mBinding.btOpenPdf.click {
            // 测试本地小文件，1M
            //PDFPreViewActivity.start(requireActivity(), "file:///android_asset/demo.pdf")
            // 测试本地大文件，30M
            //PDFPreViewActivity.start(requireActivity(), "file:///android_asset/kotlin-reference.pdf")
            // 测试网络大文件，30M
            PDFPreViewActivity.start(
                requireActivity(),
                "https://kotlinlang.org/docs/kotlin-reference.pdf?_ga=2.194794596.877109590.1651038040-806405991.1640094785"
            )
        }
        mBinding.btOpenExplosion.click {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter.setNewInstance(
            mutableListOf(
                CardExampleVo(
                    getString(R.string.str_display_title, "视频播放"),
                    "视频播放演示",
                    { startActivity(Intent(context, ImageBoxActivity::class.java)) },
                    iconUrl = ConstHelper.getDiffImage(ConstHelper.IMAGE_SOURCE_GIF)
                ),
                CardExampleVo(
                    getString(R.string.str_display_title, "爆炸效果"),
                    "这个应该属于动画相关",
                    { startActivity(Intent(requireActivity(), ExplosionActivity::class.java)) },
                    iconUrl = ConstHelper.getDiffImage(ConstHelper.IMAGE_SOURCE_GIF)
                ),
                CardExampleVo(
                    getString(R.string.str_display_title, "打开PDF"),
                    "使用H5+JS的方法实现",
                    {
                        PDFPreViewActivity.start(
                            requireActivity(),
                            "https://kotlinlang.org/docs/kotlin-reference.pdf?_ga=2.194794596.877109590.1651038040-806405991.1640094785"
                        )
                    },
                    iconUrl = ConstHelper.getDiffImage(ConstHelper.IMAGE_SOURCE_GIF)
                )
            )
        )
    }

    override fun fitsSystemWindows(): Boolean = false
}