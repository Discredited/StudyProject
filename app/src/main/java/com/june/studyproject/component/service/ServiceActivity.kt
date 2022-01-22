package com.june.studyproject.component.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.view.View
import com.june.base.basic.part.BaseActivity
import com.june.studyproject.R
import com.june.studyproject.component.service.TestBindService.TestBinder
import com.june.studyproject.databinding.ActivityServiceBinding
import timber.log.Timber

class ServiceActivity : BaseActivity<ActivityServiceBinding>(), View.OnClickListener, ServiceConnection {

    private var isBindService = false

    override fun initView() {
        mBinding.tvBindService.setOnClickListener(this)
        mBinding.tvBindService.setOnClickListener(this)
        mBinding.tvUnbindService.setOnClickListener(this)
        mBinding.tvStopService.setOnClickListener(this)
        mBinding.tvToNextBind.setOnClickListener(this)
    }

    override fun loadData() {}

    override fun onDestroy() {
        if (isBindService) {
            unbindService(this)
            isBindService = false
        }
        super.onDestroy()
        //从destroy的activity启动服务
        //startService(new Intent(this, TestBindService.class));
        //从destroy的activity绑定服务
        //这样的会在activity destroy后绑定服务导致内存泄漏（虽然不应该在destroy后还绑定服务...）
        //bindService(new Intent(this, TestBindService.class), this,
        // BIND_AUTO_CREATE);
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_start_service -> startService(
                    Intent(this, TestBindService::class.java)
            )
            R.id.tv_bind_service -> bindService(
                    Intent(this, TestBindService::class.java),
                    this,
                    Context.BIND_AUTO_CREATE
            )
            R.id.tv_unbind_service -> {
                //如果不判断是否绑定，直接unBindService 就会喜提异常：java.lang
                // .IllegalArgumentException: Service not registered
                if (isBindService) {
                    unbindService(this)
                    isBindService = false
                }
            }
            R.id.tv_stop_service -> stopService(Intent(this,
                    TestBindService::class.java))
            R.id.tv_to_next_bind -> start(this)
        }
    }

    override fun onServiceConnected(name: ComponentName, service: IBinder) {
        isBindService = true
        Timber.e("onServiceConnected: 连接成功:${name.className}")
        Timber.e("onServiceConnected: ${
            (service as TestBinder).service
                    .serviceInformation
        }")
    }

    override fun onServiceDisconnected(name: ComponentName) {
        isBindService = false
        Timber.e("onServiceDisconnected: 绑定失败:${name.className}")
    }

    companion object {
        fun start(context: Context) {
            val starter = Intent(context, ServiceActivity::class.java)
            context.startActivity(starter)
        }
    }
}