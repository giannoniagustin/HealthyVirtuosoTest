package com.example.healthyvirtuosotest.core.abstraction.app

import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("results")
    var movieResults: List<T> = listOf(),
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("total_results")
    val totalResults: Int = 0,
)