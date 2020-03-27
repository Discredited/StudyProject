package com.june.studyproject.base.component;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public abstract class BaseAppActivity<T extends ViewDataBinding> extends AppCompatActivity {

    protected Activity mActivity;
    protected T mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!handIntent(getIntent())) {
            finish();
            return;
        }
        mActivity = this;
        mBinding = DataBindingUtil.setContentView(this, getLayoutResId());
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
