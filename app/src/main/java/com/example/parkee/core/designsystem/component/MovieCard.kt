package com.example.parkee.core.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.parkee.core.designsystem.theme.ParkeeTheme
import com.example.parkee.domain.model.Movie

@Composable
fun MovieCard(
    movie: Movie,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(120.dp)
            .clip(MaterialTheme.shapes.small)
            .clickable(onClick = onClick),

        ) {
        MoviePoster(
            posterUrl = movie.posterUrl,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = movie.title,
            style = MaterialTheme.typography.bodyMedium,
            minLines = 2,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(horizontal = 4.dp)
        )
        Text(
            text = movie.releaseDate,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
            modifier = Modifier.padding(horizontal = 4.dp)
        )
    }
}

@Preview
@Composable
private fun MovieCardPreview() {
    ParkeeTheme {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            MovieCard(Movie(1, "Dune", "", null, null, "2021-10-22", 8.0), {})
            MovieCard(
                Movie(
                    2,
                    "The Lord of the Rings: The Fellowship of the Ring",
                    "",
                    null,
                    null,
                    "2001-12-19",
                    8.4
                ), {})
        }
    }
}