package com.june.studyproject.component.recycler.rvadapter.normal.holder

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.bumptech.glide.Glide
import com.june.studyproject.R
import com.june.studyproject.component.recycler.rvadapter.vo.NormalInterface
import com.june.studyproject.component.recycler.rvadapter.vo.NormalSimpleImage

class SimpleImageViewHolder(view: View) : NormalBaseViewHolder(view) {

    override fun onBindData(data: NormalInterface, position: Int) {
        val item = data as NormalSimpleImage
        itemView.findViewById<AppCompatTextView>(R.id.tvSimpleImageTitle).text = item.title
        Glide.with(itemView.context).load(item.imageUrl).into(itemView.findViewById<AppCompatImageView>(R.id.ivSimpleImage))
    }
}