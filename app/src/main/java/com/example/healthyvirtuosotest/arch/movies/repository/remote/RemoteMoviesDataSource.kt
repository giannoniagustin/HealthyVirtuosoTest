package com.example.healthyvirtuosotest.arch.movies.repository.remote

import com.example.healthyvirtuosotest.arch.movies.repository.service.MoviesServices
import com.example.healthyvirtuosotest.core.abstraction.app.BaseDataSource
import javax.inject.Inject

class MoviesRemoteDataSource @Inject constructor(
    private val categoriesServices: MoviesServices,
) : BaseDataSource() {
    suspend fun getPopularMovies() = getResultServices { categoriesServices.popularMovies() }
}