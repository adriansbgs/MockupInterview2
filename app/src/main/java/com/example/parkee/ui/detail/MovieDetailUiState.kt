package com.example.parkee.ui.detail

import com.example.parkee.core.common.AppError
import com.example.parkee.domain.model.MovieDetail
import com.example.parkee.domain.model.Review

sealed interface MovieDetailUiState {
    data object Loading : MovieDetailUiState
    data class Success(
        val detail: MovieDetail,
        val review: List<Review>,
        val isFavorite: Boolean,
    ) : MovieDetailUiState

    data class Error(val error: AppError) : MovieDetailUiState
}