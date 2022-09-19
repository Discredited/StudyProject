package com.june.studyproject.component.handler

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import com.june.studyproject.base.app.StudyBaseActivity
import com.june.studyproject.databinding.ActivityPermissionsBinding
import timber.log.Timber

/**
 * Handler演示的Activity
 *
 * 2022/9/16
 * @author June
 */
class HandlerActivity : StudyBaseActivity<ActivityPermissionsBinding>() {

    override fun initView() {
        // 直接创建一个Handler
        val handler = Handler(Looper.getMainLooper())
        val runnable = Runnable {
            Timber.d("我将在未来某个时间点被执行")
        }
        handler.post(runnable)
    }

    override fun loadData() {
        val handler = Handler(Looper.getMainLooper(), object : Handler.Callback {
            override fun handleMessage(msg: Message): Boolean {
                return false
            }
        })

        val message = Message.obtain().apply {
            what = 1
            data = Bundle()
        }
        handler.sendMessage(message)

        handler.post(Runnable{
            // 消息处理逻辑
        })
    }

    private fun test1(){
        Thread(Runnable {
            Looper.prepare()
            val handler = Handler(Looper.myLooper()!!)
            Looper.loop()
        }).start()
    }

}