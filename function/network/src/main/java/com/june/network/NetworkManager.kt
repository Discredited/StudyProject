package com.june.network

import com.google.gson.Gson
import com.june.network.log.OkHttpLogInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 网络请求管理
 *
 * 2022/11/1
 * @author June
 */
object NetworkManager {

    private lateinit var mOkHttpClient: OkHttpClient
    private lateinit var mRetrofit: Retrofit

    /**
     * 初始化网络管理器
     */
    fun initialize(baseUrl: String) {
        val okHttpBuilder = OkHttpClient.Builder().retryOnConnectionFailure(true)
        okHttpBuilder.addInterceptor(OkHttpLogInterceptor())
        mOkHttpClient = okHttpBuilder.build()

        mRetrofit = Retrofit.Builder()
            .client(mOkHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
    }

    private fun checkInitStatus() {
        if (!this::mOkHttpClient.isInitialized || !this::mRetrofit.isInitialized) {
            throw RuntimeException("Please call NetworkManager.initialize() first!")
        }
    }
}