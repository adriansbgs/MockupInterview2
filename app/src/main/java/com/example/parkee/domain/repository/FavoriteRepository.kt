package com.example.parkee.domain.repository

import com.example.parkee.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun observeFavorites(): Flow<List<Movie>>
    fun observeIsFavorite(movieId: Int): Flow<Boolean>
    suspend fun toggle(movie: Movie)
}