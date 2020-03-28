package com.june.studyproject.base.app

import android.app.Application
import timber.log.Timber

class StudyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}