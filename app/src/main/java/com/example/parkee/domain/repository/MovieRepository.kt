package com.example.parkee.domain.repository

import com.example.parkee.core.common.DataResult
import com.example.parkee.domain.model.Movie
import com.example.parkee.domain.model.MovieDetail
import com.example.parkee.domain.model.Review

interface MovieRepository {
    suspend fun getPopularMovies(): DataResult<List<Movie>>
    suspend fun getTopRatedMovies(): DataResult<List<Movie>>
    suspend fun getNowPlayingMovies(): DataResult<List<Movie>>
    suspend fun getMovieDetail(movieId: Int): DataResult<MovieDetail>
    suspend fun getMovieReviews(movieId: Int): DataResult<List<Review>>
}