package com.example.healthyvirtuosotest.arch.movies.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SpokenLanguage(
    val iso_639_1: String,
    val name: String
) : Parcelable