package com.example.parkee.ui.favorite.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.parkee.R
import com.example.parkee.core.designsystem.component.EmptyState
import com.example.parkee.ui.favorite.FavoriteUiState
import com.example.parkee.ui.favorite.FavoriteViewModel

@Composable
fun FavoriteRoute(
    onMovieClick: (Int) -> Unit,
    onBackClick: () -> Unit,
    viewModel: FavoriteViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    FavoriteScreen(uiState, onMovieClick, onBackClick)
}

@Composable
fun FavoriteScreen(
    uiState: FavoriteUiState,
    onMovieClick: (Int) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {/* TODO: TopAppBar "Favorite Movie" + back */ }) { padding ->
        when {
            uiState.isLoading -> {
                // TODO: Loading
            }

            uiState.movies.isEmpty() -> EmptyState(
                title = stringResource(R.string.favorite_empty_title),
                subtitle = stringResource(R.string.favorite_empty_subtitle),
                icon = Icons.Default.FavoriteBorder,
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            )

            else -> LazyColumn(modifier = Modifier.padding(padding)) {
                items(uiState.movies, key = { it.id }) { movie ->
                    FavoriteMovieRow(movie = movie, onClick = { onMovieClick(movie.id) })
                }
            }
        }
    }

}