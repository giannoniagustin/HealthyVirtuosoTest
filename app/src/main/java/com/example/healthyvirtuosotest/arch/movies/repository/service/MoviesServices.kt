package com.example.healthyvirtuosotest.arch.movies.repository.service

import com.example.healthyvirtuosotest.arch.movies.domain.model.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesServices {
    @GET("movie/popular")
    suspend fun popularMovies(@Query("api_key") key: String): Response<List<Movie>>
}