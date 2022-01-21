package com.june.studyproject.component.recycler.rvadapter.normal.holder

import android.view.View
import com.june.studyproject.component.recycler.rvadapter.normal.holder.BaseViewHolder
import com.june.studyproject.component.recycler.rvadapter.vo.NormalInterface

abstract class NormalBaseViewHolder(view: View) : BaseViewHolder(view) {

    abstract fun onBindData(data: NormalInterface, position: Int)
}