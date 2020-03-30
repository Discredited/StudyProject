package com.june.studyproject.component.activity

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.june.studyproject.R
import kotlinx.android.synthetic.main.item_record_display.view.*

class RecordDisplayAdapter : BaseQuickAdapter<RecordDisplayVo, BaseViewHolder>(R.layout.item_record_display) {

    override fun convert(helper: BaseViewHolder, item: RecordDisplayVo) {
        helper.itemView.tvRecordMillis.text = item.millis
        helper.itemView.tvRecordHour.text = item.hour
        helper.itemView.tvRecordMinute.text = item.minute
        helper.itemView.tvRecordSecond.text = item.second
        helper.itemView.tvRecordTitle.text = item.title
        helper.itemView.tvRecordDesc.text = item.desc
    }
}