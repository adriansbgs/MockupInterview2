package com.example.parkee.util

import com.example.parkee.core.common.DataResult
import com.example.parkee.domain.model.Movie
import com.example.parkee.domain.model.MovieDetail
import com.example.parkee.domain.model.Review
import com.example.parkee.domain.repository.MovieRepository

class FakeMovieRepository : MovieRepository {
    var popularResult: DataResult<List<Movie>> = DataResult.Success(emptyList())
    var topRatedResult: DataResult<List<Movie>> = DataResult.Success(emptyList())
    var nowPlayingResult: DataResult<List<Movie>> = DataResult.Success(emptyList())

    var popularCallCount = 0
        private set
    var topRatedCallCount = 0
        private set
    var nowPlayingCallCount = 0
        private set

    override suspend fun getPopularMovies(): DataResult<List<Movie>> {
        popularCallCount++
        return popularResult
    }

    override suspend fun getTopRatedMovies(): DataResult<List<Movie>> {
        topRatedCallCount++
        return topRatedResult
    }

    override suspend fun getNowPlayingMovies(): DataResult<List<Movie>> {
        nowPlayingCallCount++
        return nowPlayingResult
    }

    override suspend fun getMovieDetail(movieId: Int): DataResult<MovieDetail> {
        throw NotImplementedError("tidak dipakai di HomeViewModelTest")
    }

    override suspend fun getMovieReviews(movieId: Int): DataResult<List<Review>> {
        throw NotImplementedError("tidak dipakai di HomeViewModelTest")
    }
}