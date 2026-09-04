package com.example.parkee.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parkee.core.common.DataResult
import com.example.parkee.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadAll()
    }

    private fun loadAll() {
        loadSection(MovieSectionType.POPULAR)
        loadSection(MovieSectionType.TOP_RATED)
        loadSection(MovieSectionType.NOW_PLAYING)
    }

    fun retrySection(type: MovieSectionType) = loadSection(type)

    private fun loadSection(type: MovieSectionType) {
        viewModelScope.launch {
            updateSection(type, SectionState.Loading)

            val result = when (type) {
                MovieSectionType.POPULAR -> repository.getPopularMovies()
                MovieSectionType.TOP_RATED -> repository.getTopRatedMovies()
                MovieSectionType.NOW_PLAYING -> repository.getNowPlayingMovies()
            }
            val state = when (result) {
                is DataResult.Success -> SectionState.Success(result.data)
                is DataResult.Failure -> SectionState.Error(result.error)
            }
            updateSection(type, state)
        }
    }

    private fun updateSection(type: MovieSectionType, state: SectionState) {
        _uiState.update {
            when (type) {
                MovieSectionType.POPULAR -> it.copy(popular = state)
                MovieSectionType.TOP_RATED -> it.copy(topRated = state)
                MovieSectionType.NOW_PLAYING -> it.copy(nowPlaying = state)
            }
        }
    }
}