package com.example.parkee.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.parkee.core.designsystem.theme.ParkeeTheme
import com.example.parkee.domain.model.Movie

@Composable
fun PlaceholderBox(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .background(MaterialTheme.colorScheme.surfaceVariant)
    )
}

@Composable
fun MovieCarouselPlaceholder(modifier: Modifier = Modifier, itemCount: Int = 4) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        repeat(itemCount) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                PlaceholderBox(
                    Modifier
                        .width(120.dp)
                        .height(180.dp)
                )
                PlaceholderBox(Modifier
                    .width(120.dp)
                    .height(14.dp))
                PlaceholderBox(Modifier
                    .width(84.dp)
                    .height(14.dp))
                PlaceholderBox(Modifier
                    .width(60.dp)
                    .height(12.dp))
            }
        }
    }
}

@Preview(widthDp = 360, heightDp = 320)
@Composable
private fun MovieCarouselPlaceholderPreview() {
    ParkeeTheme {
        Surface {
            MovieCarouselPlaceholder()
        }
    }
}