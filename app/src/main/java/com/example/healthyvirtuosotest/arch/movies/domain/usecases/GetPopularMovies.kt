package com.example.healthyvirtuosotest.arch.movies.domain.usecases

import com.example.healthyvirtuosotest.arch.movies.repository.MoviesRepository
import javax.inject.Inject

class GetPopularMovies @Inject constructor(private val repository: MoviesRepository) {

    suspend operator fun invoke() = repository.getPopularMovies()

}

