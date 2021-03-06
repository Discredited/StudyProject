package com.june.studyproject.component.recycler.rvadapter

import android.content.Intent
import com.june.base.basic.ext.click
import com.june.base.basic.part.BaseActivity
import com.june.studyproject.component.recycler.rvadapter.normal.NormalListActivity
import com.june.studyproject.databinding.ActivityRecyclerViewAdapterBinding

class RecyclerViewAdapterActivity : BaseActivity<ActivityRecyclerViewAdapterBinding>() {

    override fun viewBinding(): ActivityRecyclerViewAdapterBinding {
        return ActivityRecyclerViewAdapterBinding.inflate(layoutInflater)
    }

    override fun initView() {
        mBinding.tvNormalMultiLayout.click {
            startActivity(Intent(this, NormalListActivity::class.java))
        }
    }

    override fun loadData() {
    }
}