package com.june.studyproject.component.recycler.rvadapter.normal.holder

import android.view.View
import com.june.studyproject.component.recycler.rvadapter.normal.NormalBaseViewHolder
import com.june.studyproject.component.recycler.rvadapter.normal.vo.NormalInterface
import com.june.studyproject.component.recycler.rvadapter.normal.vo.NormalMultiText
import kotlinx.android.synthetic.main.item_multi_complex_text.view.*

class MultiTextViewHolder(view: View) : NormalBaseViewHolder(view) {

    override fun onBindData(data: NormalInterface, position: Int) {
        val item = data as NormalMultiText
        itemView.tvMultiTextTitle.text = item.title
        itemView.tvMultiTextContent.text = item.content
        itemView.tvMultiTextSubContent.text = item.subContent
    }
}