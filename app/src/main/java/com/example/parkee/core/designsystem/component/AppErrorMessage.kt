package com.example.parkee.core.designsystem.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.parkee.R
import com.example.parkee.core.common.AppError

@Composable
fun AppError.toMessage(): String = when (this) {
    AppError.NoConnection -> stringResource(R.string.error_no_connection)
    AppError.Timeout -> stringResource(R.string.error_timeout)
    AppError.Unauthorized -> stringResource(R.string.error_unauthorized)
    AppError.NotFound -> stringResource(R.string.error_not_found)
    is AppError.Server -> stringResource(R.string.error_server)
    is AppError.Unknown -> stringResource(R.string.error_unknown)
}
