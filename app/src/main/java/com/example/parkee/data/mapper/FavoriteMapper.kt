package com.example.parkee.data.mapper

import com.example.parkee.data.local.FavoriteMovieEntity
import com.example.parkee.domain.model.Movie

fun Movie.toEntity(savedAt: Long = System.currentTimeMillis()): FavoriteMovieEntity =
    FavoriteMovieEntity(
        id = id,
        title = title,
        overview = overview,
        posterUrl = posterUrl,
        backdropUrl = backdropUrl,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        savedAt = savedAt
    )

fun FavoriteMovieEntity.toDomain(): Movie =
    Movie(
        id = id,
        title = title,
        overview = overview,
        posterUrl = posterUrl,
        backdropUrl = backdropUrl,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
    )