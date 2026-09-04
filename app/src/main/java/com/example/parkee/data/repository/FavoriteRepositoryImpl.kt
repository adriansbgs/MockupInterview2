package com.example.parkee.data.repository

import com.example.parkee.data.local.FavoriteMovieDao
import com.example.parkee.data.mapper.toDomain
import com.example.parkee.data.mapper.toEntity
import com.example.parkee.domain.model.Movie
import com.example.parkee.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val dao: FavoriteMovieDao,
) : FavoriteRepository {
    override fun observeFavorites(): Flow<List<Movie>> =
        dao.observeAll().map { list -> list.map { it.toDomain() } }


    override fun observeIsFavorite(movieId: Int): Flow<Boolean> =
        dao.observeIsFavorite(movieId)


    override suspend fun toggle(movie: Movie) =
        if (dao.observeIsFavorite(movie.id).first()) {
            dao.delete(movie.id)
        } else {
            dao.insert(movie.toEntity())
        }
}