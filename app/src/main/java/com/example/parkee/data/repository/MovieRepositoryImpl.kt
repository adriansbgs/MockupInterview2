package com.example.parkee.data.repository

import com.example.parkee.core.common.DataResult
import com.example.parkee.core.network.safeApiCall
import com.example.parkee.data.mapper.toDomain
import com.example.parkee.data.remote.MovieApiService
import com.example.parkee.domain.model.Movie
import com.example.parkee.domain.model.MovieDetail
import com.example.parkee.domain.model.Review
import com.example.parkee.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: MovieApiService
) : MovieRepository {
    override suspend fun getPopularMovies(): DataResult<List<Movie>> =
        safeApiCall { apiService.getPopularMovies().results.toDomain() }


    override suspend fun getTopRatedMovies(): DataResult<List<Movie>> =
        safeApiCall { apiService.getTopRatedMovies().results.toDomain() }

    override suspend fun getNowPlayingMovies(): DataResult<List<Movie>> =
        safeApiCall { apiService.getNowPlayingMovies().results.toDomain() }

    override suspend fun getMovieDetail(movieId: Int): DataResult<MovieDetail> =
        safeApiCall { apiService.getMovieDetails(movieId).toDomain() }

    override suspend fun getMovieReviews(movieId: Int): DataResult<List<Review>> =
        safeApiCall { apiService.getMovieReviews(movieId).results.map { it.toDomain() } }

}