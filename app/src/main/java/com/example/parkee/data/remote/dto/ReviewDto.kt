package com.example.parkee.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewDto(
    val id: String,
    val author: String = "",
    val content: String = "",
    @SerialName("created_at") val createdAt: String = "",
    @SerialName("author_details") val authorDetails: AuthorDetailsDto? = null,
)

@Serializable
data class AuthorDetailsDto(
    val name: String? = null,
    val username: String? = null,
    val rating: Double? = null,
    @SerialName("avatar_path") val avatarPath: String? = null,
)

@Serializable
data class ReviewResponseDto(
    val id: Int = 0,
    val page: Int = 1,
    val results: List<ReviewDto> = emptyList()
)
