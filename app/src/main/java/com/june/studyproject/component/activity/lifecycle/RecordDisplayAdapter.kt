package com.june.studyproject.component.activity.lifecycle

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.june.studyproject.R
import com.june.studyproject.databinding.ItemRecordDisplayBinding

class RecordDisplayAdapter(list: MutableList<RecordDisplayVo>) : BaseQuickAdapter<RecordDisplayVo, BaseDataBindingHolder<ItemRecordDisplayBinding>>(R.layout.item_record_display, list) {

    override fun convert(holder: BaseDataBindingHolder<ItemRecordDisplayBinding>, item: RecordDisplayVo) {
        holder.dataBinding?.tvRecordMillis?.text = item.millis
        holder.dataBinding?.tvRecordHour?.text = item.hour
        holder.dataBinding?.tvRecordMinute?.text = item.minute
        holder.dataBinding?.tvRecordSecond?.text = item.second
        holder.dataBinding?.tvRecordTitle?.text = item.title
        holder.dataBinding?.tvRecordTitle?.setTextColor(item.titleColor)
        holder.dataBinding?.tvRecordDesc?.text = item.desc
        holder.dataBinding?.tvRecordDesc?.setTextColor(item.descColor)
    }
}