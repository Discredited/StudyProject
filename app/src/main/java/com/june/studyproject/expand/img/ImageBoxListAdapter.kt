package com.june.studyproject.expand.img

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.june.imageabout.box.ImageVo
import com.june.studyproject.R
import kotlinx.android.synthetic.main.item_image_box.view.*

class ImageBoxListAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_image_box) {

    private val imageLoader = ImageLoader()

    override fun convert(helper: BaseViewHolder, item: String) {
        val list = getList()
        helper.itemView.tv_image.text = "第 $item 组    共有${list.size}张图片"
        helper.itemView.v_image_box.setImageLoader(imageLoader)
        helper.itemView.v_image_box.setImageList(list)
    }

    private fun getList(): MutableList<ImageVo> {
        val random = (Math.random() * 10).toInt()
        val list = mutableListOf<ImageVo>()
        if (random <= 1) {
            val width = 480
            val height = 640
            list.add(ImageVo("11111", width, height))
        } else {
            for (index in 0..random) {
                list.add(ImageVo("11111", 0, 0))
            }
        }
        return list
    }
}