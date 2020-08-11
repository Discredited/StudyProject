package com.june.studyproject.component.recycler.rvadapter.normal

import android.view.View
import com.june.studyproject.component.recycler.rvadapter.normal.vo.NormalInterface

abstract class NormalBaseViewHolder(view: View) : BaseViewHolder(view) {

    abstract fun onBindData(data: NormalInterface, position: Int)
}