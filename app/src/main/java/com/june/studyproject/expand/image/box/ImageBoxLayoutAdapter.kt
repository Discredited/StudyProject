package com.june.studyproject.expand.image.box

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.june.imageabout.box.ImageBoxLayout
import com.june.studyproject.R
import com.june.studyproject.databinding.ItemImageBoxBinding

class ImageBoxLayoutAdapter : BaseQuickAdapter<ImageBoxItemVo, BaseDataBindingHolder<ItemImageBoxBinding>>(R.layout.item_image_box) {

    private val imageLoader = ImageLoader()

    override fun convert(holder: BaseDataBindingHolder<ItemImageBoxBinding>, item: ImageBoxItemVo) {
        val imageBoxLayout = holder.itemView.findViewById<ImageBoxLayout<MediaVo>>(R.id.v_image_box)

        holder.dataBinding?.tvImage?.text = item.title
        imageBoxLayout.setImageLoader(imageLoader)
        imageBoxLayout.setImageList(item.list)
    }

}