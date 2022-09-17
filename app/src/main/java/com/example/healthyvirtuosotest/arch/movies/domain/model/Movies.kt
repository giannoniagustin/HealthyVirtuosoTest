package com.example.healthyvirtuosotest.arch.movies.domain.model

data class Movie(
    val adult: Boolean = false,
    val backdrop_path: String = "",
    val belongs_to_collection: Any? = null,
    val budget: Int = 0,
    val genres: List<Genre>? = null,
    val homepage: String = "",
    val id: Int? = null,
    val imdb_id: String = "",
    val original_language: String = "",
    val original_title: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val poster_path: Any? = null,
    val production_companies: List<ProductionCompany>? = null,
    val production_countries: List<ProductionCountry>? = null,
    val release_date: String = "",
    val revenue: Int? = 0,
    val runtime: Int? = 0,
    val spoken_languages: List<SpokenLanguage>? = null,
    val status: String = "",
    val tagline: String = "",
    val title: String = "",
    val video: Boolean = false,
    val vote_average: Double = 0.0,
    val vote_count: Int = 0
) {
    companion object  {

        fun dummyMovies(): List<Movie> {
            var movies: List<Movie> = listOf()

            for (i in 1..10) {
                movies = movies.plus(Movie(title = "Movie $i"))
            }
            return movies

        }
    }

}

