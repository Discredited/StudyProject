package com.june.studyproject.component.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.june.studyproject.R
import timber.log.Timber
import java.util.*

class TestBindService : Service() {
    override fun onCreate() {
        super.onCreate()
        Timber.e("onCreate: 创建TestBindService")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Timber.e("onStartCommand: 启动TestBindService")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder? {
        Timber.e("onBind: 绑定TestBindService")
        return TestBinder()
    }

    override fun onRebind(intent: Intent) {
        super.onRebind(intent)
        Timber.e("onRebind: 再次绑定TestBindService")
    }

    override fun onUnbind(intent: Intent): Boolean {
        Timber.e("onUnbind: 解绑TestBindService")
        //return super.onUnbind(intent);
        return true
    }

    override fun stopService(name: Intent): Boolean {
        Timber.e("stopService: 关闭TestBindService")
        return super.stopService(name)
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.e("onDestroy: 销毁TestBindService")
    }

    val serviceInformation: String
        get() {
            val calendar = Calendar.getInstance()
            return getString(R.string.handle_service_time,
                             calendar[Calendar.YEAR],
                             calendar[Calendar.MONTH] + 1,
                             calendar[Calendar.DAY_OF_MONTH],
                             calendar[Calendar.HOUR],
                             calendar[Calendar.SECOND])
        }

    inner class TestBinder : Binder() {
        val service: TestBindService
            get() = this@TestBindService
    }
}