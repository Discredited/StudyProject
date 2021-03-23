package com.june.studyproject.common.adapter

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.june.studyproject.R
import com.june.studyproject.common.vo.TitleDescVo
import com.june.studyproject.databinding.ItemTitleAndDescBindingImpl

class TitleDescAdapter : BaseQuickAdapter<TitleDescVo, BaseViewHolder>(R.layout.item_title_and_desc) {

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        // 绑定 view
        DataBindingUtil.bind<ItemTitleAndDescBindingImpl>(viewHolder.itemView)
    }

    override fun convert(holder: BaseViewHolder, item: TitleDescVo) {
        DataBindingUtil.getBinding<ItemTitleAndDescBindingImpl>(holder.itemView)?.vo = item
    }

}