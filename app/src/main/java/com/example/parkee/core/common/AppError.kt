package com.example.parkee.core.common

sealed interface AppError {
    data object NoConnection : AppError
    data object Unauthorized : AppError
    data object NotFound : AppError
    data object Timeout : AppError
    data class Unknown(val cause: Throwable?) : AppError
    data class Server(val code: Int) : AppError
}

