package com.example.parkee.core.network

private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"

enum class ImageSize(val path: String) {
    POSTER_LIST("w342"),
    POSTER_DETAIL("w500"),
    BACKDROP("w780"),
    AVATAR("w185")
}

fun buildImageUrl(path: String?, size: ImageSize): String? =
    path?.let { "${IMAGE_BASE_URL}${size.path}$it" }