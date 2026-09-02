package com.example.parkee.domain.model

data class MovieDetail(
    val movie: Movie,
    val runtimeMinutes: Int?,
    val genres: List<String>,
    val tagline: String?,
)