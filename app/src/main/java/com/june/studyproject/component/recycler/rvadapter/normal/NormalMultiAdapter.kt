package com.june.studyproject.component.recycler.rvadapter.normal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.june.studyproject.R
import com.june.studyproject.component.recycler.rvadapter.normal.holder.MultiImageViewHolder
import com.june.studyproject.component.recycler.rvadapter.normal.holder.MultiTextViewHolder
import com.june.studyproject.component.recycler.rvadapter.normal.holder.SimpleImageViewHolder
import com.june.studyproject.component.recycler.rvadapter.normal.holder.SimpleTextViewHolder
import com.june.studyproject.component.recycler.rvadapter.normal.vo.NormalInterface

class NormalMultiAdapter : RecyclerView.Adapter<NormalBaseViewHolder>() {

    private val mItemList: MutableList<NormalInterface> = mutableListOf()

    override fun getItemCount(): Int = mItemList.size

    override fun getItemViewType(position: Int): Int {
        return mItemList[position].getType()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NormalBaseViewHolder {
        return createViewHolders(parent, viewType)
    }

    override fun onBindViewHolder(holder: NormalBaseViewHolder, position: Int) {
        val item = mItemList[position]
        holder.onBindData(item, position)
    }

    private fun createViewHolders(parent: ViewGroup, viewType: Int): NormalBaseViewHolder {
        when (viewType) {
            NormalInterface.TYPE_SIMPLE_IMAGE -> {
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.item_multi_simple_image, parent, false)
                return SimpleImageViewHolder(view)
            }
            NormalInterface.TYPE_MULTI_TEXT -> {
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.item_multi_complex_text, parent, false)
                return MultiTextViewHolder(view)
            }
            NormalInterface.TYPE_MULTI_IMAGE -> {
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.item_multi_complex_image, parent, false)
                return MultiImageViewHolder(view)
            }
            else -> {
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.item_multi_simple_text, parent, false)
                return SimpleTextViewHolder(view)
            }
        }
    }

    fun setNewList(list: MutableList<NormalInterface>) {
        mItemList.clear()
        mItemList.addAll(list)
    }
}