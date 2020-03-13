package com.june.studyproject

import android.view.View
import com.june.studyproject.base.BaseActivity
import com.june.studyproject.service.ServiceActivity
import com.june.studyproject.thread.ThreadPoolActivity
import com.june.studyproject.thread.rxjava.RxJavaActivity.Companion.starter

class MainActivity : BaseActivity() {

    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        findViewById<View>(R.id.tv_thread_pool).setOnClickListener { ThreadPoolActivity.start(mActivity) }
        findViewById<View>(R.id.tv_service_test).setOnClickListener { ServiceActivity.start(mActivity) }
        findViewById<View>(R.id.tv_rx_java).setOnClickListener { starter(mActivity) }
    }

    override fun loadData() {}
}