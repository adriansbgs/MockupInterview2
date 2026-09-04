package com.example.parkee.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.parkee.core.common.DataResult
import com.example.parkee.domain.repository.MovieRepository
import com.example.parkee.navigation.MovieDetailRouteKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val movieId: Int = savedStateHandle.toRoute<MovieDetailRouteKey>().movieId

    private val _uiState = MutableStateFlow<MovieDetailUiState>(MovieDetailUiState.Loading)
    val uiState: StateFlow<MovieDetailUiState> = _uiState

    init {
        load()
    }

    fun load() {
        viewModelScope.launch {
            _uiState.value = MovieDetailUiState.Loading

            val detailDeferred = async { movieRepository.getMovieDetail(movieId) }
            val reviewsDeferred = async { movieRepository.getMovieReviews(movieId) }

            when (val detail = detailDeferred.await()) {
                is DataResult.Failure -> {
                    _uiState.value = MovieDetailUiState.Error(detail.error)
                }
                is DataResult.Success -> {
                    val reviews = (reviewsDeferred.await() as? DataResult.Success)?.data.orEmpty()
                    _uiState.value = MovieDetailUiState.Success(
                        detail = detail.data,
                        review = reviews,
                        isFavorite = false
                    )
                }
            }
        }
    }
}