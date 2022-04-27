package com.june.studyproject.component.recycler

import android.content.Intent
import com.june.base.basic.ext.click
import com.june.studyproject.base.app.StudyBaseActivity
import com.june.studyproject.base.ext.initToolbar
import com.june.studyproject.component.recycler.custom.CustomLayoutManagerActivity
import com.june.studyproject.component.recycler.rvadapter.RecyclerViewAdapterActivity
import com.june.studyproject.databinding.ActivityRecyclerBinding

/**
 * RecyclerViewActivity
 *
 * 2022年04月27日15:26:28
 *
 * @author:June
 */
class RecyclerActivity : StudyBaseActivity<ActivityRecyclerBinding>() {

    override fun initView() {
        mBinding.tlLayout.toolbar.initToolbar(javaClass.simpleName)
        mBinding.tlLayout.toolbar.setNavigationOnClickListener { onBackPressed() }

        mBinding.tvCustomLayoutManager.click {
            startActivity(Intent(this, CustomLayoutManagerActivity::class.java))
        }
        mBinding.tvItemDecoration.click {
            startActivity(Intent(this, ItemDecorationActivity::class.java))
        }
        mBinding.tvBaseAdapter.click {
            startActivity(Intent(this, RecyclerViewAdapterActivity::class.java))
        }
    }

    override fun loadData() {
    }
}