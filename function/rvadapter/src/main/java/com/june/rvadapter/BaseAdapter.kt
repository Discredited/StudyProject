package com.june.rvadapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


/**
 * Adapter基类
 *
 * @author June
 * @time 2024/5/17
 */
class BaseAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    private val mItems = mutableListOf<IItem>()

    override fun getItemViewType(position: Int): Int = position

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(mItems[viewType].getViewBinding())
    }

    override fun getItemCount(): Int = mItems.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        mItems[position].dataBind(holder.viewBinding, position)
    }
}