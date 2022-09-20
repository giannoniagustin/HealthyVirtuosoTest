package com.example.healthyvirtuosotest.arch.movies.domain.usecases

import javax.inject.Inject

class MoviesUseCase @Inject constructor(val getPopularMovies: GetPopularMovies)