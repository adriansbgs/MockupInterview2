package com.example.parkee.data.mapper

import com.example.parkee.core.common.toDisplayDate
import com.example.parkee.core.network.ImageSize
import com.example.parkee.core.network.buildImageUrl
import com.example.parkee.data.remote.dto.ReviewDto
import com.example.parkee.domain.model.Review

fun ReviewDto.toDomain(): Review = Review(
    id = id,
    author = author.ifBlank { authorDetails?.username ?: "Anonim" },
    content = content,
    rating = authorDetails?.rating,
    createdAt = createdAt.take(10).toDisplayDate(),
    avatarUrl = buildAvatarUrl(authorDetails?.avatarPath)
)

private fun buildAvatarUrl(path: String?): String? = when {
    path == null -> null
    path.startsWith("/http") -> path.drop(1)
    else -> buildImageUrl(path, ImageSize.AVATAR)
}