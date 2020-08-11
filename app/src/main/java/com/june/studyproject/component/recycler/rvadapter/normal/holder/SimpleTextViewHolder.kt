package com.june.studyproject.component.recycler.rvadapter.normal.holder

import android.view.View
import com.june.studyproject.component.recycler.rvadapter.normal.NormalBaseViewHolder
import com.june.studyproject.component.recycler.rvadapter.normal.vo.NormalInterface
import com.june.studyproject.component.recycler.rvadapter.normal.vo.NormalSimpleText
import kotlinx.android.synthetic.main.item_multi_simple_text.view.*

class SimpleTextViewHolder(view: View) : NormalBaseViewHolder(view) {

    override fun onBindData(data: NormalInterface, position: Int) {
        val item = data as NormalSimpleText
        itemView.tvSimpleTextTitle.text = item.title
        itemView.tvSimpleTextContent.text = item.content
    }
}