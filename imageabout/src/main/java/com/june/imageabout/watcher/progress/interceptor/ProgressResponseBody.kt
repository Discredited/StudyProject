package com.june.imageabout.watcher.progress.interceptor

import com.june.imageabout.watcher.progress.LoadingProgressListener
import okhttp3.MediaType
import okhttp3.ResponseBody
import okio.Buffer
import okio.BufferedSource
import okio.ForwardingSource
import okio.Okio.buffer
import okio.Source

class ProgressResponseBody(
    url: String,
    private val responseBody: ResponseBody
) : ResponseBody() {

    private var mBufferedSource: BufferedSource? = null
    private var mProgressListener: LoadingProgressListener? = ProgressInterceptor.listenerMap[url]

    override fun contentLength(): Long = responseBody.contentLength()

    override fun contentType(): MediaType? = responseBody.contentType()

    override fun source(): BufferedSource {
        if (null == mBufferedSource) {
            mBufferedSource = buffer(ProgressSource(responseBody.source()))
        }
        return mBufferedSource!!
    }

    inner class ProgressSource(source: Source) : ForwardingSource(source) {

        private var mProgressRead: Long = 0

        override fun read(sink: Buffer, byteCount: Long): Long {
            val bytesRead = super.read(sink, byteCount)
            val totalLength = responseBody.contentLength()
            if (bytesRead == -1L) {
                mProgressRead = totalLength
            } else {
                mProgressRead += bytesRead
            }
            val percent = mProgressRead.toFloat() / totalLength
            mProgressListener?.onProgress(mProgressRead, totalLength, percent)
            return bytesRead
        }
    }
}