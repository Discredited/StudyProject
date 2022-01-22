package com.june.studyproject.component.recycler.rvadapter.normal.holder

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.june.studyproject.R
import com.june.studyproject.component.recycler.rvadapter.vo.NormalInterface
import com.june.studyproject.component.recycler.rvadapter.vo.NormalSimpleText

class SimpleTextViewHolder(view: View) : NormalBaseViewHolder(view) {

    override fun onBindData(data: NormalInterface, position: Int) {
        val item = data as NormalSimpleText
        itemView.findViewById<AppCompatTextView>(R.id.tvSimpleTextTitle).text = item.title
        itemView.findViewById<AppCompatTextView>(R.id.tvSimpleTextContent).text = item.content
    }
}