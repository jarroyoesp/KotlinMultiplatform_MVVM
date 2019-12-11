package com.jarroyo.sharedcode.base

sealed class Response<out T> {
    class Success<out T>(val data: T) : Response<T>()
    data class Error(val exception: Throwable,
                     val code: Int? = null,
                     val error: Boolean? = null,
                     val errors: List<ErrorX>? = null,
                     val message: String? = null,
                     val method: String? = null,
                     val path: String? = null) : Response<Nothing>()
}

data class ErrorX(
    val message: String,
    val path: String
)