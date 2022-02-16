package com.june.studyproject.component.camera

import android.content.Context
import android.content.Intent
import com.june.studyproject.base.app.StudyBaseActivity
import com.june.studyproject.databinding.ActivityCameraBinding

class CameraActivity : StudyBaseActivity<ActivityCameraBinding>() {

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