package com.june.studyproject.component.recycler.rvadapter.normal.holder

import android.view.View
import com.bumptech.glide.Glide
import com.june.studyproject.component.recycler.rvadapter.normal.NormalBaseViewHolder
import com.june.studyproject.component.recycler.rvadapter.normal.vo.NormalInterface
import com.june.studyproject.component.recycler.rvadapter.normal.vo.NormalMultiImage
import kotlinx.android.synthetic.main.item_multi_complex_image.view.*

class MultiImageViewHolder(view: View) : NormalBaseViewHolder(view) {

    override fun onBindData(data: NormalInterface, position: Int) {
        val item = data as NormalMultiImage
        itemView.tvMultiImageTitle.text = item.title
        Glide.with(itemView.context).load(item.imageUrl1).into(itemView.ivMultiImage1)
        Glide.with(itemView.context).load(item.imageUrl2).into(itemView.ivMultiImage2)
    }
}