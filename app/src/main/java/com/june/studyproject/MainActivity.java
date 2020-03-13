package com.june.studyproject;

import android.view.View;
import android.view.View.OnClickListener;

import com.june.studyproject.service.ServiceActivity;
import com.june.studyproject.thread.ThreadPoolActivity;
import com.june.studyproject.thread.rxjava.RxJavaActivity;

public class MainActivity extends BaseAppActivity {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        findViewById(R.id.tv_thread_pool).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ThreadPoolActivity.start(mActivity);
            }
        });
        findViewById(R.id.tv_service_test).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceActivity.start(mActivity);
            }
        });
        findViewById(R.id.tv_rx_java).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                RxJavaActivity.starter(mActivity);
            }
        });
    }

    @Override
    protected void loadData() {
    }
}
