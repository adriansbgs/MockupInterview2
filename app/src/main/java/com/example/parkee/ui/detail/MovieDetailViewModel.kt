package com.example.parkee.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.parkee.core.common.AppError
import com.example.parkee.core.common.DataResult
import com.example.parkee.domain.model.MovieDetail
import com.example.parkee.domain.model.Review
import com.example.parkee.domain.repository.FavoriteRepository
import com.example.parkee.domain.repository.MovieRepository
import com.example.parkee.navigation.MovieDetailRouteKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val favoriteRepository: FavoriteRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val movieId: Int = savedStateHandle.toRoute<MovieDetailRouteKey>().movieId

    private val contentState = MutableStateFlow<DetailContentState>(DetailContentState.Loading)
    val uiState: StateFlow<MovieDetailUiState> =
        combine(
            contentState,
            favoriteRepository.observeIsFavorite(movieId),
        ) { content, isFavorite ->
            when (content) {
                DetailContentState.Loading -> MovieDetailUiState.Loading
                is DetailContentState.Error -> MovieDetailUiState.Error(content.error)
                is DetailContentState.Loaded -> MovieDetailUiState.Success(
                    detail = content.detail,
                    reviews = content.reviews,
                    isFavorite = isFavorite,
                )
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MovieDetailUiState.Loading,
        )

    init {
        load()
    }

    fun load() {
        viewModelScope.launch {
            contentState.value = DetailContentState.Loading

            val detailDeferred = async { movieRepository.getMovieDetail(movieId) }
            val reviewsDeferred = async { movieRepository.getMovieReviews(movieId) }

            contentState.value = when (val detail = detailDeferred.await()) {
                is DataResult.Failure -> DetailContentState.Error(detail.error)
                is DataResult.Success -> DetailContentState.Loaded(
                    detail = detail.data,
                    reviews = (reviewsDeferred.await() as? DataResult.Success)?.data.orEmpty(),
                )
            }
        }
    }

    fun toggleFavorite() {
        val current = contentState.value
        if (current !is DetailContentState.Loaded) return
        viewModelScope.launch {
            favoriteRepository.toggle(current.detail.movie)
        }
    }
}

private sealed interface DetailContentState {
    data object Loading : DetailContentState
    data class Loaded(val detail: MovieDetail, val reviews: List<Review>) : DetailContentState
    data class Error(val error: AppError) : DetailContentState
}