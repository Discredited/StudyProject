package com.june.studyproject.component.activity.lifecycle

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.june.studyproject.R
import com.june.studyproject.databinding.ItemRecordDisplayBinding

class RecordDisplayAdapter(list: MutableList<RecordDisplayVo>) :
    BaseQuickAdapter<RecordDisplayVo, BaseDataBindingHolder<ItemRecordDisplayBinding>>(R.layout.item_record_display, list) {

    override fun convert(holder: BaseDataBindingHolder<ItemRecordDisplayBinding>, item: RecordDisplayVo) {
        holder.dataBinding?.apply {
            tvRecordMillis.text = item.millis
            tvRecordHour.text = item.hour
            tvRecordMinute.text = item.minute
            tvRecordSecond.text = item.second
            tvRecordTitle.text = item.title
            tvRecordTitle.setTextColor(item.titleColor)
            tvRecordDesc.text = item.desc
            tvRecordDesc.setTextColor(item.descColor)
        }
    }
}