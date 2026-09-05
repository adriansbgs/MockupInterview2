package com.example.parkee.data.mapper

import com.example.parkee.core.common.toDisplayDate
import com.example.parkee.core.network.ImageSize
import com.example.parkee.core.network.buildImageUrl
import com.example.parkee.data.remote.dto.MovieDetailDto
import com.example.parkee.data.remote.dto.MovieDto
import com.example.parkee.domain.model.Movie
import com.example.parkee.domain.model.MovieDetail

fun MovieDto.toDomain(): Movie = Movie(
    id = id,
    title = title.ifBlank { "Untitled" },
    overview = overview.ifBlank { "No overview" },
    posterUrl = buildImageUrl(posterPath, ImageSize.POSTER_LIST),
    backdropUrl = buildImageUrl(backdropPath, ImageSize.BACKDROP),
    releaseDate = releaseDate.toDisplayDate(),
    voteAverage = voteAverage
)

fun MovieDetailDto.toDomain(): MovieDetail = MovieDetail(
    movie = Movie(
        id = id,
        title = title.ifBlank { "Untitled" },
        overview = overview.ifBlank { "No overview" },
        posterUrl = buildImageUrl(posterPath, ImageSize.POSTER_DETAIL),
        backdropUrl = buildImageUrl(backdropPath, ImageSize.BACKDROP),
        releaseDate = releaseDate?.toDisplayDate() ?: "-",
        voteAverage = voteAverage
    ),
    runtimeMinutes = runtime,
    genres = genres.map { it.name },
    tagline = tagline?.takeIf { it.isNotBlank() }
)

fun List<MovieDto>.toDomain(): List<Movie> = map { it.toDomain() }