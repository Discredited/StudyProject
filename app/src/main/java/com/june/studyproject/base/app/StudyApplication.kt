package com.june.studyproject.base.app

import android.app.Application
import com.blankj.utilcode.util.Utils
import timber.log.Timber

class StudyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Utils.init(this)
    }
}