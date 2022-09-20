package com.example.healthyvirtuosotest.arch.app.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.healthyvirtuosotest.core.abstraction.workers.Resource
import kotlinx.coroutines.Dispatchers

fun <A> serviceOperation(
    networkCall: suspend () -> Resource<A>,
    parseError: suspend (Resource<A>) -> Resource<A>,
): LiveData<Resource<A>> {
    return liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val responseStatus: Resource<A> = networkCall.invoke()
        if (responseStatus.status == Resource.Status.ERROR) {
            emit(parseError.invoke(responseStatus))
        } else {
            emit(responseStatus)
        }
    }
}