package com.june.studyproject.expand.image.box

import android.content.Intent
import com.june.studyproject.base.app.StudyBaseActivity
import com.june.studyproject.base.ext.initToolbar
import com.june.studyproject.databinding.ActivityImageBoxBinding
import com.june.studyproject.expand.image.watcher.ImageDragLayoutActivity

class ImageBoxActivity : StudyBaseActivity<ActivityImageBoxBinding>() {

    override fun initView() {
        mBinding.tlLayout.toolbar.initToolbar(javaClass.simpleName)

        mBinding.tvBoxImageView.setOnClickListener {
            startActivity(Intent(this, BoxImageActivityBasic::class.java))
        }
        mBinding.tvBoxImageLayout.setOnClickListener {
            startActivity(Intent(this, ImageBoxLayoutActivity::class.java))
        }
        mBinding.tvBoxImageLayoutList.setOnClickListener {
            startActivity(Intent(this, ImageBoxListActivityBasic::class.java))
        }
        mBinding.tvImageDragLayout.setOnClickListener {
            startActivity(Intent(this, ImageDragLayoutActivity::class.java))
        }
    }

    override fun loadData() {
    }
}