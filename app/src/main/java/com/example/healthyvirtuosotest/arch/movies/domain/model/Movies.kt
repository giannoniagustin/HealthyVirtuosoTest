package com.example.healthyvirtuosotest.arch.movies.domain.model

class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val backdropPath: String,
    val releaseDate: String,
    val voteAverage: Double,
    val voteCount: Int,
    val popularity: Double,
    val isAdult: Boolean,
    val isVideo: Boolean,
    val isFavorite: Boolean
) {
    fun getPosterUrl(): String {
        return "https://image.tmdb.org/t/p/w500$posterPath"
    }

    fun getBackdropUrl(): String {
        return "https://image.tmdb.org/t/p/w500$backdropPath"
    }
}