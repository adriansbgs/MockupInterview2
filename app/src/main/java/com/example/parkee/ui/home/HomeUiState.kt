package com.example.parkee.ui.home

import com.example.parkee.core.common.AppError
import com.example.parkee.domain.model.Movie

sealed interface SectionState {
    data object Loading : SectionState
    data class Success(val movies: List<Movie>) : SectionState
    data class Error(val error: AppError) : SectionState
}

data class HomeUiState(
    val popular: SectionState = SectionState.Loading,
    val topRated: SectionState = SectionState.Loading,
    val nowPlaying: SectionState = SectionState.Loading,
)

enum class MovieSectionType { POPULAR, TOP_RATED, NOW_PLAYING }