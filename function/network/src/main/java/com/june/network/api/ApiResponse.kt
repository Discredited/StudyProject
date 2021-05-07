package com.june.network.api

/**
 * 网络响应密封类
 *
 * 对象数据类型：data:{}
 */
sealed class ApiResponse<T> {

    /**
     * 网络请求成功
     */
    data class Success<T>(val data: T?) : ApiResponse<T>()

    /**
     * 网络请求失败
     */
    data class Fail(val code: Int, val msg: String?) : ApiResponse<Nothing>()

    /**
     * 网络请求异常
     */
    data class Error(val error: String?) : ApiResponse<Nothing>()
}
