package com.june.network.api

/**
 * 网络响应密封类
 *
 * 数组数据类型：data:[]
 */
sealed class ApiPageResponse<out T : Any> {

    /**
     * 初次加载或者刷新
     */
    data class Refresh<out T : Any>(val list: T) : ApiPageResponse<T>()

    /**
     * 加载更多
     */
    data class LoadMore<out T : Any>(val list: T) : ApiPageResponse<T>()

    /**
     * 数组为空
     */
    data class Empty(val code: Int = 0, val msg: String? = "") : ApiPageResponse<Nothing>()

    /**
     * 加载失败
     */
    data class Fail(val code: Int = 0, val msg: String? = "") : ApiPageResponse<Nothing>()

    /**
     * 网络异常
     */
    data class Error(val code: Int = 0, val msg: String? = "") : ApiPageResponse<Nothing>()
}