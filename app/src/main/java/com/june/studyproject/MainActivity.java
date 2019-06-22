package com.june.studyproject;

import android.view.View;

import com.june.studyproject.thread.CacheThreadActivity;

public class MainActivity extends BaseAppActivity {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CacheThreadActivity.start(MainActivity.this);
            }
        });
    }

    @Override
    protected void loadData() {

    }
}
