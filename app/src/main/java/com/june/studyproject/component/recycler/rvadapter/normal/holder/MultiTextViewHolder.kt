package com.june.studyproject.component.recycler.rvadapter.normal.holder

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.june.studyproject.R
import com.june.studyproject.component.recycler.rvadapter.normal.NormalBaseViewHolder
import com.june.studyproject.component.recycler.rvadapter.normal.vo.NormalInterface
import com.june.studyproject.component.recycler.rvadapter.normal.vo.NormalMultiText

class MultiTextViewHolder(view: View) : NormalBaseViewHolder(view) {

    override fun onBindData(data: NormalInterface, position: Int) {
        val item = data as NormalMultiText
        itemView.findViewById<AppCompatTextView>(R.id.tvMultiTextTitle).text = item.title
        itemView.findViewById<AppCompatTextView>(R.id.tvMultiTextContent).text = item.title
        itemView.findViewById<AppCompatTextView>(R.id.tvMultiTextSubContent).text = item.title
    }
}