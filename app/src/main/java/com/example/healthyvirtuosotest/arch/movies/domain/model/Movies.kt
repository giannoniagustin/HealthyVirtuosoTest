package com.example.healthyvirtuosotest.arch.movies.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val adult: Boolean = false,
    val backdropPath: String = "",
    val belongsToCollection: String? = null,
    val budget: Int = 0,
    val genres: List<Genre>? = null,
    val homepage: String = "",
    val id: Int? = null,
    val imdbId: String = "",
    val originalLanguage: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val posterPath: String? =
        "http://www.cfpcemon.it/assets/camaleon_cms/image-not-found-4a963b95bf081c3ea02923dceaeb3f8085e1a654fc54840aac61a57a60903fef.png",
    val productionCompanies: List<ProductionCompany>? = null,
    val productionCountries: List<ProductionCountry>? = null,
    val releaseDate: String = "",
    val revenue: Int? = 0,
    val runtime: Int? = 0,
    val spokenLanguages: List<SpokenLanguage>? = null,
    val status: String = "",
    val tagline: String = "",
    val title: String = "",
    val video: Boolean = false,
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0
) : Parcelable {
    companion object {
        fun dummyMovies(): List<Movie> {
            var movies: List<Movie> = listOf()
            for (i in 1..10) {
                movies = movies.plus(
                    Movie(
                        title = "Movie $i",
                        originalLanguage = "Italian",
                        originalTitle = "Movie Original Title $i",
                        homepage = "www.ole.com.ar",
                        overview = "Para las mejores amigas Becky y Hunter, la vida trata de superar tus miedos y empujar tus límites. Sin embargo, después de subir hasta la cima de una torre de comunicaciones abandonada, se encuentran atrapadas y sin forma de bajar. A 600 metros del suelo y totalmente alejadas de la civilización, las chicas pondrán a prueba sus habilidades de escaladoras expertas y lucharán desesperadamente por sobrevivir aunque lo tengan todo en contra. ¿Lo conseguirán?",
                    )
                )
            }
            return movies
        }
    }

}

