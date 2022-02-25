package com.june.rvadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

open class BaseAdapter<T, IVC : ItemViewCreator<out T>> : RecyclerView.Adapter<ItemViewHolder>() {

    private val mItems: MutableList<out T> = mutableListOf()

    private val mCreators: MutableMap<Int, IVC> = mutableMapOf()

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
        mCreators[holder.itemViewId()]?.covert(mItems[position] as Nothing, holder)
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

    fun putItem(itemCreator: IVC) {
        mCreators[itemCreator.getItemViewId()] = itemCreator
    }


    class Builder<T, IVC : ItemViewCreator<out T>> {

        private lateinit var adapter: BaseAdapter<T, IVC>
        private val creators: MutableList<IVC> = mutableListOf()

        fun putItem(itemCreator: IVC): Builder<T, IVC> {
            creators.add(itemCreator)
            return this
        }

        fun build(): BaseAdapter<T, IVC> {
            adapter = BaseAdapter()
            creators.forEach { adapter.putItem(it) }
            return adapter
        }

    }
}