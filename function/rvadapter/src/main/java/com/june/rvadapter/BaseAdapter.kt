package com.june.rvadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class BaseAdapter : RecyclerView.Adapter<ItemViewHolder<*>>() {

    private val mItems: MutableList<Any> = mutableListOf()

    private val mCreators: Map<Int, ItemViewCreator<*>> = mutableMapOf()

    override fun getItemCount(): Int = mItems.size

    override fun getItemViewType(position: Int): Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder<*> {
        val createItemHolder = createItemViewHolder(parent, viewType)
        createItemHolder?.let { holder ->
            return holder
        }
        //请检查您的item类型是否被包含
        throw Exception("Please check if your item type is included")
    }

    override fun onBindViewHolder(holder: ItemViewHolder<*>, position: Int) {
        holder.onBind(mItems[position], position, mItems)
    }

    private fun createItemViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder<*>? {
        //有哪些类型
        val creator = mCreators[viewType]
        //根据类型创建ViewHolder
        creator?.let {
            return it.createViewHolder(LayoutInflater.from(parent.context).inflate(it.getItemViewId(), parent, false))
        }
        return null
    }

    fun putItem() {
        //resourceId
        //viewHolder
    }
}