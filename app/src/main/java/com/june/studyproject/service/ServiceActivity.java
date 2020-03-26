package com.june.studyproject.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import com.june.studyproject.base.BaseAppActivity;
import com.june.studyproject.R;
import com.june.studyproject.databinding.ActivityServiceBinding;

public class ServiceActivity extends BaseAppActivity<ActivityServiceBinding> implements View.OnClickListener, ServiceConnection {

    private static final String TAG = "ServiceActivity";

    private boolean isBindService;

    public static void start(Context context) {
        Intent starter = new Intent(context, ServiceActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_service;
    }

    @Override
    protected void initView() {
        mBinding.tvBindService.setOnClickListener(this);
        mBinding.tvStartService.setOnClickListener(this);
        mBinding.tvUnbindService.setOnClickListener(this);
        mBinding.tvStopService.setOnClickListener(this);
        mBinding.tvToNextBind.setOnClickListener(this);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void onDestroy() {
        if (isBindService) {
            unbindService(this);
            isBindService = false;
        }
        super.onDestroy();
        //从destroy的activity启动服务
        //startService(new Intent(this, TestBindService.class));

        //从destroy的activity绑定服务
        //这样的会在activity destroy后绑定服务导致内存泄漏（虽然不应该在destroy后还绑定服务...）
        //bindService(new Intent(this, TestBindService.class), this, BIND_AUTO_CREATE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_start_service:
                startService(new Intent(this, TestBindService.class));
                break;
            case R.id.tv_bind_service:
                bindService(new Intent(this, TestBindService.class), this, BIND_AUTO_CREATE);
                break;
            case R.id.tv_unbind_service:
                //如果不判断是否绑定，直接unBindService
                //就会喜提异常：java.lang.IllegalArgumentException: Service not registered
                if (isBindService) {
                    unbindService(this);
                    isBindService = false;
                }
                break;
            case R.id.tv_stop_service:
                stopService(new Intent(this, TestBindService.class));
                break;
            case R.id.tv_to_next_bind:
                ServiceActivity.start(this);
                break;
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        isBindService = true;
        Log.e(TAG, "onServiceConnected: 连接成功:" + name.getClassName());
        Log.e(TAG, "onServiceConnected: " + ((TestBindService.TestBinder) service).getService().getServiceInformation());
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        isBindService = false;
        Log.e(TAG, "onServiceDisconnected: 绑定失败:" + name.getClassName());
    }
}
