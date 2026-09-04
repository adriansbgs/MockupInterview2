package com.example.parkee.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailDto(
    val id: Int,
    val title: String = "",
    val overview: String = "",
    val tagline: String? = null,
    val runtime: Int? = null,
    val genres: List<GenreDto> = emptyList(),
    @SerialName("poster_path") val posterPath: String? = null,
    @SerialName("backdrop_path") val backdropPath: String? = null,
    @SerialName("release_date") val releaseDate: String? = null,
    @SerialName("vote_average") val voteAverage: Double = 0.0,
)

@Serializable
data class GenreDto(
    val id: Int,
    val name: String = "",
)