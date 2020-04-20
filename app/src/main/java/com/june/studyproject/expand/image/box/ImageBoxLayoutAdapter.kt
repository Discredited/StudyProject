package com.june.studyproject.expand.image.box

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.june.imageabout.box.ImageBoxLayout
import com.june.studyproject.R
import kotlinx.android.synthetic.main.item_image_box.view.*

class ImageBoxLayoutAdapter : BaseQuickAdapter<ImageBoxItemVo, BaseViewHolder>(R.layout.item_image_box) {

    private val imageLoader = ImageLoader()

    override fun convert(helper: BaseViewHolder, item: ImageBoxItemVo) {
        val imageBoxLayout = helper.itemView.findViewById<ImageBoxLayout<MediaVo>>(R.id.v_image_box)

        helper.itemView.tv_image.text = item.title
        imageBoxLayout.setImageLoader(imageLoader)
        imageBoxLayout.setImageList(item.list)
    }
}