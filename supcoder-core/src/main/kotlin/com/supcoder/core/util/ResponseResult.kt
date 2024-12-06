package com.supcoder.core.util

/**
 * ResponseResult
 *
 * @author lee
 * @date 2024/12/6
 */
class ResponseResult<T> {

    var code: Int = 0
    var message: String = ""
    var data: T? = null

    constructor(code: Int, message: String, data: T?) {
        this.code = code
        this.message = message
        this.data = data
    }

    constructor(code: Int, message: String) {
        this.code = code
        this.message = message
    }



    companion object {
        fun <T> success(data: T?): ResponseResult<T> {
            return ResponseResult(200, "success", data)
        }

        fun <T> error(code: Int, message: String): ResponseResult<T> {
            return ResponseResult(code, message)
        }

        fun <T> error(message: String): ResponseResult<T> {
            return ResponseResult(500, message)
        }

        fun <T> error(e: Exception): ResponseResult<T> {
            return ResponseResult(500, e.message ?: "error")
        }

    }

}