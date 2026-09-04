package com.example.parkee.core.network

import com.example.parkee.core.common.AppError
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Throwable.toAppError(): AppError = when (this) {
    is UnknownHostException -> AppError.NoConnection
    is SocketTimeoutException -> AppError.Timeout
    is HttpException -> when (code()) {
        401, 403 -> AppError.Unauthorized
        404 -> AppError.NotFound
        in 500..599 -> AppError.Server(code())
        else -> AppError.Unknown(this)
    }
    is IOException -> AppError.NoConnection
    else -> AppError.Unknown(this)
}