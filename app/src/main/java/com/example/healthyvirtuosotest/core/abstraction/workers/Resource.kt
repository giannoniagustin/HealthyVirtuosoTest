package com.example.healthyvirtuosotest.core.abstraction.workers

import com.example.healthyvirtuosotest.core.exception.model.ErrorApi


class Resource<out T>(
    var status: Status,
    val data: T?,
    val errorApi: ErrorApi? = null,
    val message: String? = null,
    val exception: Exception? = null,
) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T?, status: Status): Resource<T> {
            return Resource(
                status = status,
                data,
                errorApi = null,
                message = null,
                exception = null
            )
        }

        fun <T> error(
            message: String,
            data: T? = null,
            exception: Exception? = null,
            errorApi: ErrorApi? = null,
            status: Status = Status.ERROR,
        ): Resource<T> {
            return Resource(
                status = status,
                data = data,
                message = message,
                exception = exception,
                errorApi = errorApi
            )
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(
                status = Status.LOADING,
                data = data,
                errorApi = null,
                message = null,
                exception = null
            )
        }
    }
}