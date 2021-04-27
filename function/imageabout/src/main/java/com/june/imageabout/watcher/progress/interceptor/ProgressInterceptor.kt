package com.june.imageabout.watcher.progress.interceptor

import com.june.imageabout.watcher.progress.LoadingProgressListener
import okhttp3.Interceptor
import okhttp3.Response

class ProgressInterceptor : Interceptor {

    companion object {
        val listenerMap: HashMap<String, LoadingProgressListener> = hashMapOf()

        fun addListener(url: String, listener: LoadingProgressListener) {
            listenerMap[url] = listener
        }

        fun removeListener(url: String) {
            listenerMap.remove(url)
        }
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        val url = request.url().toString()
        val body = response.body()
        body?.let {
            return response.newBuilder().body(ProgressResponseBody(url, it)).build()
        }
        return response
    }
}