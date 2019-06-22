package com.june.studyproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!handIntent(getIntent())) {
            finish();
            return;
        }

        setContentView(getLayoutResId());
        initView();
        loadData();
    }

    protected boolean handIntent(Intent intent) {
        return true;
    }

    /**
     * 获取布局资源文件ID
     *
     * @return
     */
    protected abstract int getLayoutResId();

    /**
     * 初始化View
     */
    protected abstract void initView();

    /**
     * 加载数据
     */
    protected abstract void loadData();
}
