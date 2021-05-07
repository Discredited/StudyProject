package com.june.network.api

/**
 * 分页帮助类
 */
class PaginationHelper(var page: Int = 1, val row: Int = 15, var hasMore: Boolean = true) {

    fun reset() {
        page = 1
        hasMore = true
    }
}