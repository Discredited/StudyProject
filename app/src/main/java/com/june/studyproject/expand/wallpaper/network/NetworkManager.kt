package com.june.studyproject.expand.wallpaper.network

import com.june.network.log.HeadersInterceptor
import com.june.network.log.OkHttpLogInterceptor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import timber.log.Timber
import java.security.KeyStore
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

/**
 * 网络请求管理器
 *
 * 2023/5/30
 * @author June
 */
class NetworkManager {

    private val trustManagers: Array<TrustManager> = arrayOf(
        object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<X509Certificate?>?, authType: String?) {}

            @Throws(CertificateException::class)
            override fun checkServerTrusted(chain: Array<X509Certificate?>?, authType: String?) {
                try {
                    val trustManagerFactory: TrustManagerFactory =
                        TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
                    trustManagerFactory.init(null as KeyStore?)
                    for (trustManager in trustManagerFactory.trustManagers) {
                        (trustManager as X509TrustManager).checkServerTrusted(chain, authType)
                    }
                } catch (e: java.lang.Exception) {
                    throw CertificateException(e)
                }
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        }
    )

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .readTimeout(10L, TimeUnit.SECONDS)
            .writeTimeout(10L, TimeUnit.SECONDS)
            .connectTimeout(10L, TimeUnit.SECONDS)
            .apply {
                val sslContext = SSLContext.getInstance("TLS")
                sslContext.init(null, trustManagers, SecureRandom())
                sslSocketFactory(sslContext.socketFactory, trustManagers[0] as X509TrustManager)

                addInterceptor(HeadersInterceptor())
                addInterceptor(OkHttpLogInterceptor())
            }
            .build()
    }

    suspend fun requestUrl(url: String): String {
        return withContext(Dispatchers.IO) {
            val request = Request.Builder().url(url).method("GET", null).build()
            try {
                val response = okHttpClient.newCall(request).execute()
                response.body?.string() ?: ""
            } catch (e: Exception) {
                Timber.e("网络请求失败:${e}")
                ""
            }
        }
    }
}