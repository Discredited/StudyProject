package com.june.studyproject.expand.thread

import android.view.View
import com.june.base.basic.part.BaseActivity
import com.june.studyproject.R
import com.june.studyproject.databinding.ActivityThreadPoolActivityBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

class ThreadPoolActivity : BaseActivity<ActivityThreadPoolActivityBinding>(), View.OnClickListener {
    private var threadCounter = 0

    override fun initView() {
        mBinding.tvCached.setOnClickListener(this);
        mBinding.tvFixed.setOnClickListener(this);
        mBinding.tvScheduled.setOnClickListener(this);
        mBinding.tvSingle.setOnClickListener(this);
    }

    override fun loadData() {}
    private fun doExecute(executorService: ExecutorService) {
        for (i in 0..9) {
            executorService.execute {
                val current = getString(
                    R.string.current_thread_information,
                    Thread.currentThread().name
                )
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
                val current = getString(
                    R.string.current_thread_information,
                    Thread.currentThread().name
                )
                runOnUiThread {
                    setInformation(current, false)
                    threadCounter++
                    if (threadCounter == 10) {
                        scheduledExecutorService.shutdown()
                    }
                }
            }, 0, 1, TimeUnit.SECONDS
        )
    }

    private fun setInformation(information: String, isClear: Boolean) {
        if (isClear) {
            mBinding.tvInformation.text = ""
        }
        mBinding.tvInformation.append(information)
        mBinding.tvInformation.append("\n")
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
        mBinding.tvCached.isSelected = v.id == mBinding.tvCached.id
        mBinding.tvFixed.isSelected = v.id == mBinding.tvFixed.id
        mBinding.tvScheduled.isSelected = v.id == mBinding.tvScheduled.id
        mBinding.tvSingle.isSelected = v.id == mBinding.tvSingle.id
    }

    //    companion object {
    //        fun start(context: Context) {
    //            val starter = Intent(context, ThreadPoolActivity::class.java)
    //            context.startActivity(starter)
    //        }
    //    }

}