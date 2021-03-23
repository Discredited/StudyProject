package com.june.studyproject.library.okhttp

import android.content.Intent
import com.june.studyproject.R
import com.june.studyproject.base.component.BasicActivity
import com.june.studyproject.base.ext.click
import com.june.studyproject.base.ext.setLinearManager
import com.june.studyproject.common.adapter.column.*
import kotlinx.android.synthetic.main.activity_ok_http.*

class OkHttpActivity : BasicActivity() {

    private val mAdapter = ColumnAdapter()

    override fun getLayoutResId(): Int = R.layout.activity_ok_http

    override fun initView() {
        rvOkHttp.setLinearManager()
        rvOkHttp.adapter = mAdapter
        rvOkHttp.setHasFixedSize(true)

        btDownload.click {
            startActivity(Intent(this, DownloadActivity::class.java))
        }
    }

    override fun loadData() {
        requestData()
    }

    private fun requestData() {
        val list = mutableListOf<ColumnInterface>()
        list.add(ColumnTitleVo("什么是OkHttp"))
        list.add(ColumnTextVo("OKHttp是Square公司开源的一个网络请求框架，也是目前市面上使用最多的网络框架之一"))
        list.add(ColumnTextVo("OKHttp是Square公司开源的一个网络请求框架，也是目前市面上使用最多的网络框架之一"))
        list.add(ColumnTitleVo("OkHttp大致流程"))
        list.add(ColumnImageVo("https://user-gold-cdn.xitu.io/2018/12/20/167ca038637fa3aa?imageView2/0/w/1280/h/960/format/webp/ignore-error/1"))
        list.add(ColumnTitleVo("OkHttp使用方法"))
        list.add(ColumnTextVo("val client = OkHttpClient()"))
        list.add(ColumnTextVo("public Builder() {\n" +
            "        dispatcher = new Dispatcher();\n" +
            "        protocols = DEFAULT_PROTOCOLS;\n" +
            "        connectionSpecs = DEFAULT_CONNECTION_SPECS;\n" +
            "        eventListenerFactory = EventListener.factory(EventListener.NONE);\n" +
            "        proxySelector = ProxySelector.getDefault();\n" +
            "        cookieJar = CookieJar.NO_COOKIES;\n" +
            "        socketFactory = SocketFactory.getDefault();\n" +
            "        hostnameVerifier = OkHostnameVerifier.INSTANCE;\n" +
            "        certificatePinner = CertificatePinner.DEFAULT;\n" +
            "        proxyAuthenticator = Authenticator.NONE;\n" +
            "        authenticator = Authenticator.NONE;\n" +
            "        connectionPool = new ConnectionPool();\n" +
            "        dns = Dns.SYSTEM;\n" +
            "        followSslRedirects = true;\n" +
            "        followRedirects = true;\n" +
            "        retryOnConnectionFailure = true;\n" +
            "        connectTimeout = 10_000;\n" +
            "        readTimeout = 10_000;\n" +
            "        writeTimeout = 10_000;\n" +
            "        pingInterval = 0;\n" +
            "}"))
        mAdapter.setNewInstance(list)
    }
}