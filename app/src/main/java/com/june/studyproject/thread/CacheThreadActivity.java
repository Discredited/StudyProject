package com.june.studyproject.thread;

import android.content.Context;
import android.content.Intent;

import com.june.studyproject.BaseAppActivity;
import com.june.studyproject.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CacheThreadActivity extends BaseAppActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, CacheThreadActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_cache_thread_activity;
    }

    @Override
    protected void initView() {
        ExecutorService executorService = Executors.newCachedThreadPool();
    }

    @Override
    protected void loadData() {

    }
}
