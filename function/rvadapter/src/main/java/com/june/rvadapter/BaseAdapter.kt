package com.june.rvadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

open class BaseAdapter<T> : RecyclerView.Adapter<ItemViewHolder>() {

    private val mItems: MutableList<T> = mutableListOf()

    private val mCreators: MutableMap<Int, ItemViewCreator<T>> = mutableMapOf()

    override fun getItemCount(): Int = mItems.size

    override fun getItemViewType(position: Int): Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val createItemHolder = createItemViewHolder(parent, viewType)
        createItemHolder?.let { holder ->
            return holder
        }
        //请检查您的item类型是否被包含
        throw Exception("Please check whether your item type is included!")
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        mCreators[holder.itemViewId()]?.covert(mItems[position], holder)
    }

    private fun createItemViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder? {
        //有哪些类型
        val creator = mCreators[viewType]
        //根据类型创建ViewHolder
        creator?.let {
            return it.createViewHolder(LayoutInflater.from(parent.context).inflate(it.getItemViewId(), parent, false))
        }
        return null
    }

    fun putItem(itemCreator: ItemViewCreator<T>) {
        mCreators[itemCreator.getItemViewId()] = itemCreator
    }


    class Builder<T> {

        private lateinit var adapter: BaseAdapter<T>
        private val creators: MutableList<ItemViewCreator<T>> = mutableListOf()

        fun putItem(itemCreator: ItemViewCreator<T>): Builder<T> {
            creators.add(itemCreator)
            return this
        }

        fun build(): BaseAdapter<T> {
            adapter = BaseAdapter()
            creators.forEach { adapter.putItem(it) }
            return adapter
        }

    }
}