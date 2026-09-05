package com.example.parkee.ui.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.parkee.R
import com.example.parkee.core.designsystem.component.EmptyState
import com.example.parkee.core.designsystem.component.PlaceholderBox
import com.example.parkee.ui.favorite.component.FavoriteMovieRow

@Composable
fun FavoriteRoute(
    onMovieClick: (Int) -> Unit,
    onBackClick: () -> Unit,
    viewModel: FavoriteViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    FavoriteScreen(uiState, onMovieClick, onBackClick)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    uiState: FavoriteUiState,
    onMovieClick: (Int) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.favorite_title)) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            stringResource(R.string.action_back),
                        )
                    }
                },
            )
        }) { padding ->
        when {
            uiState.isLoading -> {
                Column(
                    modifier = Modifier.padding(padding),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    repeat(4) {
                        Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                            PlaceholderBox(Modifier
                                .width(80.dp)
                                .height(120.dp))
                            Spacer(Modifier.width(12.dp))
                            Column(
                                modifier = Modifier.weight(1f),
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                            ) {
                                PlaceholderBox(Modifier
                                    .fillMaxWidth()
                                    .height(20.dp))
                                PlaceholderBox(Modifier
                                    .width(100.dp)
                                    .height(14.dp))
                                PlaceholderBox(Modifier
                                    .fillMaxWidth()
                                    .height(14.dp))
                            }
                        }
                    }
                }
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