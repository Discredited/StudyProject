package com.june.studyproject.expand.image.box

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.june.imageabout.box.ImageVo
import com.june.studyproject.R
import kotlinx.android.synthetic.main.item_image_box.view.*

class ImageBoxListAdapter : BaseQuickAdapter<ImageBoxItemVo, BaseViewHolder>(R.layout.item_image_box) {

    private val imageLoader = ImageLoader()

    override fun convert(helper: BaseViewHolder, item: ImageBoxItemVo) {
        helper.itemView.tv_image.text = item.title
        helper.itemView.v_image_box.setImageLoader(imageLoader)
        helper.itemView.v_image_box.setImageList(item.list)
    }
}