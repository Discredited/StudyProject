package com.june.rvadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class BaseAdapter : RecyclerView.Adapter<ItemViewHolder>() {

    private val mData: MutableList<Any> = mutableListOf()

    override fun getItemCount(): Int = mData.size

    override fun getItemViewType(position: Int): Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val createItemHolder = createItemHolder(parent, viewType)
        createItemHolder?.let { holder ->
            return holder
        }
        //请检查您的类型是否被包含
        throw Exception("Please check if your type is included")
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
    }

    private fun createItemHolder(parent: ViewGroup, viewType: Int): ItemViewHolder? {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(0, parent, false))
    }
}