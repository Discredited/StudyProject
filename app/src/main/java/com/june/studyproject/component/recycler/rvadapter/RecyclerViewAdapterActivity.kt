package com.june.studyproject.component.recycler.rvadapter

import android.content.Intent
import com.june.base.basic.ext.click
import com.june.studyproject.base.app.StudyBaseActivity
import com.june.studyproject.component.recycler.rvadapter.normal.NormalListActivity
import com.june.studyproject.databinding.ActivityRecyclerViewAdapterBinding

class RecyclerViewAdapterActivity : StudyBaseActivity<ActivityRecyclerViewAdapterBinding>() {

    override fun initView() {
        mBinding.tvNormalMultiLayout.click {
            startActivity(Intent(this, NormalListActivity::class.java))
        }
        mBinding.tvBaseMultiLayout.click {
        }
    }

    override fun loadData() {
    }
}