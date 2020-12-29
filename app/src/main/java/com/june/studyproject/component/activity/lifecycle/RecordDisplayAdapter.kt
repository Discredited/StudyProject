package com.june.studyproject.component.activity.lifecycle

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.june.studyproject.R
import kotlinx.android.synthetic.main.item_record_display.view.*

class RecordDisplayAdapter(list: MutableList<RecordDisplayVo>) : BaseQuickAdapter<RecordDisplayVo, BaseViewHolder>(
    R.layout.item_record_display,
    list
) {

    override fun convert(holder: BaseViewHolder, item: RecordDisplayVo) {
        holder.itemView.tvRecordMillis.text = item.millis
        holder.itemView.tvRecordHour.text = item.hour
        holder.itemView.tvRecordMinute.text = item.minute
        holder.itemView.tvRecordSecond.text = item.second
        holder.itemView.tvRecordTitle.text = item.title
        holder.itemView.tvRecordTitle.setTextColor(item.titleColor)
        holder.itemView.tvRecordDesc.text = item.desc
        holder.itemView.tvRecordDesc.setTextColor(item.descColor)
    }
}