package com.june.studyproject.component.recycler.rvadapter

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.june.rvadapter.ItemViewCreator
import com.june.rvadapter.ItemViewHolder
import com.june.studyproject.R

class SimpleItemCreator : ItemViewCreator<String>() {

    override fun getItemViewId(): Int = R.layout.item_simple

    override fun createViewHolder(view: View): ItemViewHolder {
        return object : ItemViewHolder(view) {
            override fun <T> onBind(item: T, position: Int, itemList: MutableList<Any>) {
                itemView.findViewById<AppCompatTextView>(R.id.tv_simple).text = item as String
            }
        }
    }

    override fun covert(item: Any, holder: ItemViewHolder) {
        holder.onBind(item, holder.layoutPosition, mutableListOf())
    }
}