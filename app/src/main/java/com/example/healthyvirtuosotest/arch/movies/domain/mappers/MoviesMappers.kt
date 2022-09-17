package com.example.healthyvirtuosotest.arch.movies.domain.mappers

import com.example.healthyvirtuosotest.arch.movies.domain.model.Movie
import com.example.healthyvirtuosotest.core.abstraction.app.BaseDataSource
import com.example.healthyvirtuosotest.core.abstraction.workers.Resource
import com.example.healthyvirtuosotest.core.enums.ErrorCode
import com.example.healthyvirtuosotest.core.extensions.decodeErrorApi
import com.example.healthyvirtuosotest.core.extensions.decodeErrorApiToException
import com.example.healthyvirtuosotest.core.extensions.decodeExceptionApi
import com.google.gson.Gson

class MoviesMappers : BaseDataSource() {
    suspend fun movies(response: MutableList<Movie>) = executeProcess {
        Resource.success(
            data = response,
            status = Resource.Status.SUCCESS
        )
    }

    suspend fun moviesError(response: Resource<MutableList<Movie>>) = executeProcess {

        val decode = Gson().toJson(response.exception)

        if (decode.equals("null") || decode == null) {
            Resource.error<MutableList<Movie>>(
                message = "",
                data = null,
                exception = decodeErrorApiToException(errorCode = ErrorCode.UNKNOWN),
                errorApi = decodeErrorApi(errorCode = ErrorCode.UNKNOWN),
            )
        } else {
            Resource.error<MutableList<Movie>>(
                message = "",
                data = null,
                exception = decodeExceptionApi(exception = response.exception!!),
                errorApi = decodeErrorApi(response.exception)
            )
        }
    }
}