package com.june.network.log

import okhttp3.Interceptor
import okhttp3.MultipartBody
import okhttp3.Response
import okio.Buffer
import org.json.JSONObject
import timber.log.Timber
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit

/**
 * 网络请求日志拦截类
 *
 * 2022/7/7
 * @author June
 */
class OkHttpLogInterceptor : Interceptor {

    private val utf8 = Charset.forName("UTF-8")

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val startTime = System.nanoTime()
        // 打印请求
        val contentType = request.body?.contentType()
        val sb = StringBuilder()
        sb.append("\n")
        sb.append("<-- START HTTP (").append(request.url).append(") -->\n")
        sb.append("method: ${request.method} \n")
        sb.append("content-type: $contentType \n")

        // 打印headers
        sb.append("header ==> \n")
        val headers = request.headers
        for (index in 0 until headers.size) {
            sb.append(headers.name(index)).append(": ").append(headers.value(index)).append("\n")
        }

        // 打印请求参数
        sb.append("params ==> \n")

        if ("POST" == request.method) {
            if (request.body is MultipartBody) {
                sb.append("文件类型参数")
            } else {
                request.body?.let { requestBody ->
                    val buffer = Buffer()
                    requestBody.writeTo(buffer)
                    requestBody.contentType()?.charset(utf8)
                    val params = buffer.readString(utf8)
                    sb.append(params)
                }
            }
        } else {
            val httpUrl = request.url.newBuilder().build()
            httpUrl.queryParameterNames.forEach { name ->
                sb.append(name).append(": ").append(httpUrl.queryParameter(name))
            }
        }
        // 打印响应参数
        val response = chain.proceed(request)
        val tookTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime)
        val responseBody = response.body
        val contentLength = responseBody?.contentLength() ?: -1L
        val bodySize = if (contentLength != -1L) {
            "${contentLength}-byte"
        } else {
            "unknown-length"
        }
        sb.append("\nbody ==> \n")
        sb.append(response.code)
            .append(' ')
            .append(response.message)
            .append(' ')
            .append(response.request.url)
            .append(" (")
            .append(tookTime)
            .append("ms")
            .append(", ")
            .append(bodySize)
            .append(" body")
            .append(')')
            .append(" \n")

        val source = responseBody?.source()?.let { source ->
            source.request(Long.MAX_VALUE) // Buffer the entire body.
            sb.append("response contentType ==> ${responseBody.contentType()} \n")
            if (contentLength > 0) {
                val buffer = source.buffer
                if (responseBody.contentType()?.toString()?.startsWith("application/json") == true) {
                    try {
                        val json = JSONObject(buffer.clone().readString(utf8)).toString(4)
                        sb.append("response ==> \n")
                            .append(json)
                            .append(" \n")
                    } catch (e: Exception) {
                        Timber.e("response error ==> \n")
                        Timber.e(e)
                    }
                }
            }
            source
        }


        sb.append("<-- END HTTP (").append(source?.buffer?.size ?: 0).append("-byte body) -->")
        Timber.d(sb.toString())
        return response
    }
}