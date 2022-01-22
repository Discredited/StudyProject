package com.june.studyproject.component.camera

import android.content.Context
import android.content.Intent
import com.june.base.basic.part.BaseActivity
import com.june.studyproject.databinding.ActivityCameraBinding

class CameraActivity : BaseActivity<ActivityCameraBinding>() {

    override fun initView() {
    }

    override fun loadData() {
    }

    companion object {
        fun starter(context: Context) {
            context.startActivity(Intent(context, CameraActivity::class.java))
        }
    }
}