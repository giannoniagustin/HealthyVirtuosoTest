package com.example.healthyvirtuosotest.arch.movies.repository.service

import com.example.healthyvirtuosotest.core.abstraction.app.ApiResponse
import com.example.healthyvirtuosotest.arch.movies.domain.model.Movie
import retrofit2.Response
import retrofit2.http.GET

interface MoviesServices {
    @GET("movie/popular")
    suspend fun popularMovies(): Response<ApiResponse<Movie>>
}