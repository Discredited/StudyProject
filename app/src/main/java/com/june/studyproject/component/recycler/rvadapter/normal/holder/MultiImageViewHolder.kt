package com.june.studyproject.component.recycler.rvadapter.normal.holder

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.bumptech.glide.Glide
import com.june.studyproject.R
import com.june.studyproject.component.recycler.rvadapter.vo.NormalInterface
import com.june.studyproject.component.recycler.rvadapter.vo.NormalMultiImage

class MultiImageViewHolder(view: View) : NormalBaseViewHolder(view) {

    override fun onBindData(data: NormalInterface, position: Int) {
        val item = data as NormalMultiImage
        itemView.findViewById<AppCompatTextView>(R.id.tvMultiImageTitle).text = item.title

        Glide.with(itemView.context).load(item.imageUrl1).into(itemView.findViewById<AppCompatImageView>(R.id.ivMultiImage1))
        Glide.with(itemView.context).load(item.imageUrl2).into(itemView.findViewById<AppCompatImageView>(R.id.ivMultiImage2))
    }
}