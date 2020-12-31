package com.june.studyproject.expand.thread

import android.content.Context
import android.content.Intent
import android.view.View
import com.june.studyproject.R
import com.june.studyproject.base.component.BasicActivity
import kotlinx.android.synthetic.main.activity_thread_pool_activity.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

class ThreadPoolActivity : BasicActivity(), View.OnClickListener {
    private var threadCounter = 0
    override fun getLayoutResId(): Int {
        return R.layout.activity_thread_pool_activity
    }

    override fun initView() {
        tv_cached.setOnClickListener(this);
        tv_fixed.setOnClickListener(this);
        tv_scheduled.setOnClickListener(this);
        tv_single.setOnClickListener(this);
    }

    override fun loadData() {}
    private fun doExecute(executorService: ExecutorService) {
        for (i in 0..9) {
            executorService.execute {
                val current = getString(R.string.current_thread_information,
                                        Thread.currentThread().name)
                runOnUiThread { setInformation(current, false) }
            }
        }
        if (!executorService.isShutdown) {
            executorService.shutdown()
        }
    }

    private fun doSchedule(scheduledExecutorService: ScheduledExecutorService) {
        //延迟一秒后执行
//        scheduledExecutorService.schedule(new Runnable() {
//            @Override
//            public void run() {
//                final String current = getString(R.string
//                .current_thread_information, Thread.currentThread().getName());
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        setInformation(current, false);
//                    }
//                });
//            }
//        }, 1, TimeUnit.SECONDS);
        threadCounter = 0
        //延迟零秒后执行，每隔一秒执行一次
        scheduledExecutorService.scheduleAtFixedRate(
            {
                val current = getString(R.string.current_thread_information,
                                        Thread.currentThread().name)
                runOnUiThread {
                    setInformation(current, false)
                    threadCounter++
                    if (threadCounter == 10) {
                        scheduledExecutorService.shutdown()
                    }
                }
            }, 0, 1, TimeUnit.SECONDS)
    }

    private fun setInformation(information: String, isClear: Boolean) {
        if (isClear) {
            tv_information.text = ""
        }
        tv_information.append(information)
        tv_information.append("\n")
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_cached -> {
                setInformation("newCachedThreadPool:", true)
                doExecute(Executors.newCachedThreadPool())
            }
            R.id.tv_fixed -> {
                setInformation("newFixedThreadPool:", true)
                doExecute(Executors.newFixedThreadPool(5))
            }
            R.id.tv_scheduled -> {
                setInformation("newScheduledThreadPool:", true)
                doSchedule(Executors.newScheduledThreadPool(5))
            }
            R.id.tv_single -> {
                setInformation("newSingleThreadPool:", true)
                doExecute(Executors.newSingleThreadExecutor())
            }
        }
        tv_cached.isSelected = v.id == tv_cached.id
        tv_fixed.isSelected = v.id == tv_fixed.id
        tv_scheduled.isSelected = v.id == tv_scheduled.id
        tv_single.isSelected = v.id == tv_single.id
    }

    companion object {
        fun start(context: Context) {
            val starter = Intent(context, ThreadPoolActivity::class.java)
            context.startActivity(starter)
        }
    }
}