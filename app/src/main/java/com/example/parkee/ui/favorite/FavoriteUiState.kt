package com.example.parkee.ui.favorite

import com.example.parkee.domain.model.Movie

data class FavoriteUiState(
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
)
