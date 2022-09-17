package com.example.healthyvirtuosotest.core.abstraction.app

import android.util.Log
import com.example.healthyvirtuosotest.core.abstraction.workers.Resource

import retrofit2.Response

abstract class BaseDataSource {

    @Deprecated("no use in new api")
    @Suppress("BlockingMethodInNonBlockingContext")
    protected suspend fun <T> getResult(call: suspend () -> T): Resource<T> {
        return try {
            val response = call()
            if (response != null) {
                Resource.success(response, Resource.Status.SUCCESS)
            } else {
                error(
                    message = "response null",
                    exception = Exception("response null"),
                    data = response
                )
            }
        } catch (exception: Exception) {
            error(message = exception.toString(), exception = exception, data = null)
        }
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    protected suspend fun <T> getResultServices(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.success(body, Resource.Status.SUCCESS)
            }
            return error(
                " ${response.code()} ${response.message()}",
                exception = Exception(response.errorBody()!!.string())
            )
        } catch (exception: Exception) {
            return error(message = exception.toString(), exception = exception)
        }
    }

    @Deprecated("no use in new api")
    private fun <T> error(message: String, exception: Exception, data: T?): Resource<T> {
        Log.e("remoteDataSource", "$message $exception")
        return Resource.error(
            "Network call has failed for a following reason: $message",
            exception = exception,
            data = data
        )
    }

    private fun <T> error(message: String, exception: Exception): Resource<T> {
        Log.e("remoteDataSource", "$message $exception")
        return Resource.error(
            "Network call has failed for a following reason: $message",
            exception = exception
        )
    }

    protected suspend fun <T> execute(call: suspend () -> T) {
        call()
    }

    protected suspend fun <T> executeMappers(call: suspend () -> T): Resource<T> {
        return Resource.success(call(), Resource.Status.SUCCESS)
    }

    protected suspend fun <T> executeProcessSingle(call: suspend () -> Resource<T>): Resource<T> {
        return call()
    }

    protected suspend fun <T> executeMappersError(call: suspend () -> Resource<T>): Resource<T> {
        return call()
    }

    protected suspend fun <T> executeProcess(call: suspend () -> T): T {
        return call()
    }


    protected suspend fun <T> executeQueryDataBase(call: suspend () -> T): Resource<T> {
        return Resource.success(call(), Resource.Status.SUCCESS)
    }
}