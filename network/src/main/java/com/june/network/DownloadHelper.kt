package com.june.network

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.FileOutputStream

class DownloadHelper {

    private val mClient = OkHttpClient()

    fun startDownload(url: String, filePath: String): String? {
        val request = Request.Builder().url(url).build()
        val newCall = mClient.newCall(request)
        val response = newCall.execute()
        val inputStream = response.body?.byteStream()
        val outputStream = FileOutputStream(File(filePath))
        return inputStream?.use {
            outputStream.use { fileOut ->
                val length = it.copyTo(fileOut)
                if (length > 0) {
                    filePath
                } else {
                    null
                }
            }
        }
    }
}