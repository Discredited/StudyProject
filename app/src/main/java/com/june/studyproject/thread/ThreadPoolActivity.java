package com.june.studyproject.thread;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.june.studyproject.base.component.BaseAppActivity;
import com.june.studyproject.R;
import com.june.studyproject.databinding.ActivityThreadPoolActivityBinding;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolActivity extends BaseAppActivity<ActivityThreadPoolActivityBinding> implements View.OnClickListener {

    private int threadCounter;

    public static void start(Context context) {
        Intent starter = new Intent(context, ThreadPoolActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_thread_pool_activity;
    }

    @Override
    protected void initView() {
        mBinding.tvCached.setOnClickListener(this);
        mBinding.tvFixed.setOnClickListener(this);
        mBinding.tvScheduled.setOnClickListener(this);
        mBinding.tvSingle.setOnClickListener(this);
    }

    @Override
    protected void loadData() {
    }

    private void doExecute(ExecutorService executorService) {
        for (int i = 0; i < 10; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    final String current = getString(R.string.current_thread_information, Thread.currentThread().getName());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setInformation(current, false);
                        }
                    });
                }
            });
        }
        if (!executorService.isShutdown()) {
            executorService.shutdown();
        }
    }

    private void doSchedule(final ScheduledExecutorService scheduledExecutorService) {
        //延迟一秒后执行
//        scheduledExecutorService.schedule(new Runnable() {
//            @Override
//            public void run() {
//                final String current = getString(R.string.current_thread_information, Thread.currentThread().getName());
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        setInformation(current, false);
//                    }
//                });
//            }
//        }, 1, TimeUnit.SECONDS);

        threadCounter = 0;

        //延迟零秒后执行，每隔一秒执行一次
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                final String current = getString(R.string.current_thread_information, Thread.currentThread().getName());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setInformation(current, false);
                        threadCounter++;
                        if (threadCounter == 10) {
                            scheduledExecutorService.shutdown();
                        }
                    }
                });
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    private void setInformation(String information, boolean isClear) {
        if (isClear) {
            mBinding.tvInformation.setText("");
        }
        mBinding.tvInformation.append(information);
        mBinding.tvInformation.append("\n");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cached:
                setInformation("newCachedThreadPool:", true);
                doExecute(Executors.newCachedThreadPool());
                break;
            case R.id.tv_fixed:
                setInformation("newFixedThreadPool:", true);
                doExecute(Executors.newFixedThreadPool(5));
                break;
            case R.id.tv_scheduled:
                setInformation("newScheduledThreadPool:", true);
                doSchedule(Executors.newScheduledThreadPool(5));
                break;
            case R.id.tv_single:
                setInformation("newSingleThreadPool:", true);
                doExecute(Executors.newSingleThreadExecutor());
                break;
        }
        mBinding.tvCached.setSelected(v.getId() == mBinding.tvCached.getId());
        mBinding.tvFixed.setSelected(v.getId() == mBinding.tvFixed.getId());
        mBinding.tvScheduled.setSelected(v.getId() == mBinding.tvScheduled.getId());
        mBinding.tvSingle.setSelected(v.getId() == mBinding.tvSingle.getId());
    }
}
