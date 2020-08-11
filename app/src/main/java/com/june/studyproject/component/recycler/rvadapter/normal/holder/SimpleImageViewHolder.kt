package com.june.studyproject.component.recycler.rvadapter.normal.holder

import android.view.View
import com.bumptech.glide.Glide
import com.june.studyproject.component.recycler.rvadapter.normal.NormalBaseViewHolder
import com.june.studyproject.component.recycler.rvadapter.normal.vo.NormalInterface
import com.june.studyproject.component.recycler.rvadapter.normal.vo.NormalSimpleImage
import kotlinx.android.synthetic.main.item_multi_simple_image.view.*

class SimpleImageViewHolder(view: View) : NormalBaseViewHolder(view) {

    override fun onBindData(data: NormalInterface, position: Int) {
        val item = data as NormalSimpleImage
        itemView.tvSimpleImageTitle.text = item.title
        Glide.with(itemView.context).load(item.imageUrl).into(itemView.ivSimpleImage)
    }
}