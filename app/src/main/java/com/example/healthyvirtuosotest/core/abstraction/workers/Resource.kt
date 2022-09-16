package com.example.healthyvirtuosotest.core.abstraction.workers


 class Resource<out T>(
    var status: Status,
    val data: T?,
    val errorApi: String?=null,
    val message: String?=null,
    val exception: Exception?=null
) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING,
        DISCONNECTED,
        CONNECTED
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
            errorApi: String? = null,
            status: Status = Status.ERROR
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
        fun <T> disconnected(
            message: String,
            data: T? = null,
            exception: Exception? = null,
            errorApi: String? = null,
            status: Status = Status.DISCONNECTED
        ): Resource<T> {
            return Resource(
                status = status,
                data = data,
                message = message,
                exception = exception,
                errorApi = errorApi
            )
        }
        fun <T> connected(data: T?, status: Status): Resource<T> {
            return Resource(
                status = status,
                data,
                errorApi = null,
                message = null,
                exception = null
            )
        }
    }
}