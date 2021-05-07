package com.june.network.api

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiHelper {

    private lateinit var mRetrofit: Retrofit
    private lateinit var mOkHttpClient: OkHttpClient

    private val mInterceptor: MutableList<Interceptor> = mutableListOf()

    fun initialize(baseUrl: String, isDebug: Boolean) {
        val okHttpBuilder = OkHttpClient.Builder().retryOnConnectionFailure(true)
        mInterceptor.forEach {
            okHttpBuilder.addInterceptor(it)
        }
        if (isDebug) {
            val logInterceptor = HttpLoggingInterceptor()
            logInterceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpBuilder.addInterceptor(logInterceptor)
        }
        mOkHttpClient = okHttpBuilder.build()

        mRetrofit = Retrofit.Builder()
                .client(mOkHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .build()
    }

    fun addInterceptor(interceptor: Interceptor) {
        mInterceptor.add(interceptor)
    }

    fun <T> create(clazz: Class<T>): T {
        return mRetrofit.create(clazz)
    }

    companion object {
        private val mInstance: ApiHelper by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ApiHelper()
        }

        fun getInstance(): ApiHelper {
            return mInstance
        }

        fun <T> create(clazz: Class<T>): T {
            return mInstance.create(clazz)
        }
    }
}