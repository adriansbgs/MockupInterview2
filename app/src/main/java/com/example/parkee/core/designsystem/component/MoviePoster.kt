package com.example.parkee.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.parkee.core.designsystem.theme.ParkeeTheme

@Composable
fun MoviePoster(
    posterUrl: String?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    aspectRatio: Float = 2f / 3f,
) {
    Box(
        modifier = modifier
            .aspectRatio(aspectRatio)
            .clip(MaterialTheme.shapes.small)
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        if (posterUrl == null) {
            Icon(
                imageVector = Icons.Default.Movie,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.Center),
            )
        } else {
            AsyncImage(
                model = posterUrl,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
                error = rememberVectorPainter(Icons.Default.Movie),
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Preview
@Composable
private fun MoviePosterPreview() {
    ParkeeTheme {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            MoviePoster(null, null, Modifier.width(120.dp))
            MoviePoster("https://invalid", null, Modifier.width(120.dp))
        }
    }
}