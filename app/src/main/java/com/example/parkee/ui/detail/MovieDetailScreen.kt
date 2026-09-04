package com.example.parkee.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.parkee.R
import com.example.parkee.core.designsystem.component.EmptyState
import com.example.parkee.core.designsystem.component.FullScreenErrorState
import com.example.parkee.core.designsystem.component.MovieBackdrop
import com.example.parkee.core.designsystem.component.PlaceholderBox
import com.example.parkee.core.designsystem.theme.ParkeeTheme
import com.example.parkee.domain.model.Movie
import com.example.parkee.domain.model.MovieDetail
import com.example.parkee.ui.detail.component.ReviewItem

@Composable
fun MovieDetailRoute(
    onBackClick: () -> Unit,
    viewModel: MovieDetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    MovieDetailScreen(
        uiState = uiState,
        onBackClick = onBackClick,
        onRetry = viewModel::load,
        onFavoriteClick = viewModel::toggleFavorite,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(
    uiState: MovieDetailUiState,
    onBackClick: () -> Unit,
    onRetry: () -> Unit,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = (uiState as? MovieDetailUiState.Success)?.detail?.movie?.title.orEmpty(),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            stringResource(R.string.action_back)
                        )
                    }
                }
            )
        }
    ) { padding ->
        when (uiState) {
            MovieDetailUiState.Loading -> {
                Column(modifier = Modifier.padding(padding)) {
                    PlaceholderBox(
                        Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                    Spacer(Modifier.height(16.dp))
                    PlaceholderBox(
                        Modifier
                            .padding(horizontal = 16.dp)
                            .width(200.dp)
                            .height(24.dp)
                    )
                    Spacer(Modifier.height(8.dp))
                    PlaceholderBox(
                        Modifier
                            .padding(horizontal = 16.dp)
                            .width(200.dp)
                            .height(24.dp)
                    )
                }
            }

            is MovieDetailUiState.Error -> FullScreenErrorState(uiState.error, onRetry)
            is MovieDetailUiState.Success -> {
                LazyColumn(modifier = Modifier.padding(padding)) {
                    item {
                        MovieBackdrop(
                            backdropUrl = uiState.detail.movie.backdropUrl,
                            fallbackPosterUrl = uiState.detail.movie.posterUrl,
                            contentDescription = uiState.detail.movie.title,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    item {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = uiState.detail.movie.title,
                                style = MaterialTheme.typography.headlineSmall,
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(
                                text = uiState.detail.movie.releaseDate,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                            Spacer(Modifier.height(16.dp))
                            Text(
                                text = stringResource(R.string.detail_description),
                                style = MaterialTheme.typography.titleMedium,
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(
                                text = uiState.detail.movie.overview,
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                    }
                    item {
                        Text(
                            text = stringResource(R.string.detail_review),
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        )
                    }
                    if (uiState.reviews.isEmpty()) {
                        item { EmptyState(stringResource(R.string.no_review)) }
                    } else {
                        items(uiState.reviews, key = { it.id }) { ReviewItem(it) }
                    }
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.End
                        ) {
                            IconButton(onClick = onFavoriteClick) {
                                Icon(
                                    imageVector = if (uiState.isFavorite) Icons.Default.Favorite
                                    else Icons.Default.FavoriteBorder,
                                    contentDescription = stringResource(
                                        if (uiState.isFavorite) R.string.action_unfavorite
                                        else R.string.action_favorite
                                    ),
                                    tint = if (uiState.isFavorite) MaterialTheme.colorScheme.primary
                                    else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            IconButton(onClick = { /* TODO: share */ }) {
                                Icon(Icons.Default.Share, stringResource(R.string.action_share))
                            }
                        }
                    }
                }
            }
        }
    }
}

private val dummyDetail = MovieDetail(
    movie = Movie(
        1,
        "Dune: Part Two",
        "Paul Atreides unites with Chani...",
        null,
        null,
        "1 Mar 2024",
        8.2
    ),
    runtimeMinutes = 167,
    genres = listOf("Sci-Fi", "Adventure"),
    tagline = null,
)

@Preview(widthDp = 360, heightDp = 800)
@Composable
private fun MovieDetailScreenNoReviewPreview() {
    ParkeeTheme {
        MovieDetailScreen(
            uiState = MovieDetailUiState.Success(dummyDetail, emptyList(), isFavorite = false),
            onBackClick = {}, onRetry = {}, onFavoriteClick = {},
        )
    }
}

@Preview(widthDp = 360, heightDp = 800)
@Composable
private fun MovieDetailScreenLoadingPreview() {
    ParkeeTheme {
        MovieDetailScreen(
            uiState = MovieDetailUiState.Loading,
            onBackClick = {}, onRetry = {}, onFavoriteClick = {},
        )
    }
}