package com.example.parkee.ui.favorite.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.parkee.core.designsystem.component.MoviePoster
import com.example.parkee.domain.model.Movie

@Composable
fun FavoriteMovieRow(
    movie: Movie,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
    ) {
        MoviePoster(
            posterUrl = movie.posterUrl,
            contentDescription = null,
            modifier = Modifier.width(80.dp)
        )
        Spacer(Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            // TODO: title — titleMedium, maxLines 2, ellipsis
            // TODO: releaseDate — bodySmall, onSurfaceVariant
            // TODO: overview — bodySmall, maxLines 3, ellipsis
        }
    }
}