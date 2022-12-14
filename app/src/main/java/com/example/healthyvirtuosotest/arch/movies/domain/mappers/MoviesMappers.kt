package com.example.healthyvirtuosotest.arch.movies.domain.mappers

import com.example.healthyvirtuosotest.arch.movies.domain.model.Movie
import com.example.healthyvirtuosotest.core.abstraction.app.ApiResponse
import com.example.healthyvirtuosotest.core.abstraction.app.BaseDataSource
import com.example.healthyvirtuosotest.core.abstraction.workers.Resource
import com.example.healthyvirtuosotest.core.enums.ErrorCode
import com.example.healthyvirtuosotest.core.extensions.decodeErrorApi
import com.example.healthyvirtuosotest.core.extensions.decodeErrorApiToException
import com.example.healthyvirtuosotest.core.extensions.decodeExceptionApi
import com.google.gson.Gson
import javax.inject.Inject

class MoviesMappers @Inject constructor() : BaseDataSource() {
    suspend fun movies(response: List<Movie>) = executeProcess {
        Resource.success(
            data = response,
            status = Resource.Status.SUCCESS
        )
    }

    suspend fun moviesError(response: Resource<ApiResponse<Movie>>) = executeProcess {

        val decode = Gson().toJson(response.exception)

        if (decode.equals("null") || decode == null) {
            Resource.error<ApiResponse<Movie>>(
                message = "",
                data = null,
                exception = decodeErrorApiToException(errorCode = ErrorCode.UNKNOWN),
                errorApi = decodeErrorApi(errorCode = ErrorCode.UNKNOWN),
            )
        } else {
            Resource.error<ApiResponse<Movie>>(
                message = "",
                data = null,
                exception = decodeExceptionApi(exception = response.exception!!),
                errorApi = decodeErrorApi(response.exception)
            )
        }
    }
}