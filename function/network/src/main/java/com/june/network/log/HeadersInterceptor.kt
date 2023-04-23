package com.june.network.log

import android.webkit.WebSettings
import android.webkit.WebView
import com.blankj.utilcode.util.Utils
import okhttp3.Headers.Companion.toHeaders
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.util.*

/**
 * header拦截器
 *
 * 2023/4/23
 * @author June
 */
class HeadersInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val headersMap = mutableMapOf<String, String>()

        headersMap["User-Agent"] = WebSettings.getDefaultUserAgent(Utils.getApp()) ?: ""

        request.headers.let { headers ->
            headers.size.takeIf { it > 0 }?.apply {
                for (i in 0 until this) {
                    val key = headers.name(i).lowercase(Locale.CHINA)
                    headersMap[key] = headers.value(i)
                }
            }
        }

        try {
            return chain.proceed(
                chain.request()
                    .newBuilder()
                    .headers(headersMap.toHeaders())
                    .build()
            )
        } catch (e: Exception) {
            throw IOException(e)
        }
    }
}