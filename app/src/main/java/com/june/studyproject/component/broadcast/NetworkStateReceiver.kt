package com.june.studyproject.component.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build

/**
 * @author June
 * @description 网络状态广播接收者
 * @version
 * @time 2020/4/29 13:21
 */
class NetworkStateReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        //获得ConnectivityManager
//        val connectManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//
//        val isNetworkActive = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
//            val networkInfo = connectManager.activeNetworkInfo
//            networkInfo?.let {
//                it.isConnected && (it.type == ConnectivityManager.TYPE_WIFI || it.type == ConnectivityManager.TYPE_MOBILE)
//            } ?: false
//        } else {
//            //获取当前网络连接的信息
//            val network = connectManager.activeNetwork
//            network?.let {
//                val networkCapabilities = connectManager.getNetworkCapabilities(it)
//                true
//            } ?: false
//        }
    }
}