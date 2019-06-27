package com.june.studyproject.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.june.studyproject.R;

import java.util.Calendar;

public class TestBindService extends Service {

    private static final String TAG = "TestBindService";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate: 创建TestBindService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand: 启动TestBindService");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind: 绑定TestBindService");
        return new TestBinder();
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.e(TAG, "onRebind: 再次绑定TestBindService");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "onUnbind: 解绑TestBindService");
        //return super.onUnbind(intent);
        return true;
    }

    @Override
    public boolean stopService(Intent name) {
        Log.e(TAG, "stopService: 关闭TestBindService");
        return super.stopService(name);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: 销毁TestBindService");
    }

    public String getServiceInformation() {
        Calendar calendar = Calendar.getInstance();

        return getString(R.string.handle_service_time,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.HOUR),
                calendar.get(Calendar.SECOND));
    }

    public class TestBinder extends Binder {
        public TestBindService getService() {
            return TestBindService.this;
        }
    }
}
