package com.example.healthyvirtuosotest.arch.movies.repository

import com.example.healthyvirtuosotest.arch.app.common.serviceOperation
import com.example.healthyvirtuosotest.arch.movies.domain.mappers.MoviesMappers
import com.example.healthyvirtuosotest.arch.movies.repository.remote.MoviesRemoteDataSource
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val moviesRemoteDataSource: MoviesRemoteDataSource,
    private val moviesMappers: MoviesMappers
) {
    fun getPopularMovies() = serviceOperation(
        networkCall = { moviesRemoteDataSource.getPopularMovies() },
        //   Resource.success(Movie.dummyMovies(), status = Resource.Status.SUCCESS)

        parseError = { moviesMappers.moviesError(response = it) }
    )
}