package com.example.parkee.core.designsystem.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MovieBackdrop(
    backdropUrl: String?,
    fallbackPosterUrl: String?,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    MoviePoster(
        posterUrl = backdropUrl ?: fallbackPosterUrl,
        contentDescription = contentDescription,
        modifier = modifier,
        aspectRatio = 16f / 9f
    )
}