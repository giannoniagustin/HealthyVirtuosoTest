package com.example.healthyvirtuosotest.arch.app.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.healthyvirtuosotest.core.abstraction.workers.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow


fun <T> performGetOperation(networkCall: suspend () -> Resource<T>): LiveData<Resource<T>> {

    return liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val responseStatus = networkCall.invoke()
        emit(responseStatus)
    }
}

fun <T, A> performGetOperation(
    networkCall: suspend () -> Resource<A>,
    parseSuccess: suspend (Resource<A>) -> Resource<T>,
    parseError: suspend (Resource<A>) -> Resource<T>
): LiveData<Resource<T>> {

    return liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val responseStatus: Resource<A> = networkCall.invoke()
        if (responseStatus.status == Resource.Status.ERROR) {
            emit(parseError.invoke(responseStatus))
        } else {
            emit(parseSuccess.invoke(responseStatus))
        }
    }
}


fun <T, A> serviceOperation(
    networkCall: suspend () -> Resource<A>,
    parseSuccess: suspend (A) -> Resource<T>,
    parseError: suspend (Resource<A>) -> Resource<T>
): LiveData<Resource<T>> {

    return liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val responseStatus: Resource<A> = networkCall.invoke()
        if (responseStatus.status == Resource.Status.ERROR) {
            emit(parseError.invoke(responseStatus))
        } else {
            emit(parseSuccess.invoke(responseStatus.data!!))
        }
    }
}

fun <A> serviceOperation(
    networkCall: suspend () -> Resource<A>,
    parseError: suspend (Resource<A>) -> Resource<A>
): LiveData<Resource<A>> {

    return liveData(Dispatchers.IO) {
        emit(Resource.loading())
        //delay(3000)
        val responseStatus: Resource<A> = networkCall.invoke()
        if (responseStatus.status == Resource.Status.ERROR) {
            emit(parseError.invoke(responseStatus))
        } else {
            emit(responseStatus)
        }
    }
}


fun <A> serviceOperationFlow(
    networkCall: suspend () -> Resource<A>,
    parseError: suspend (Resource<A>) -> Resource<A>
) = flow<Resource<A>> {
    emit(Resource.loading())
    val responseStatus: Resource<A> = networkCall.invoke()
    if (responseStatus.status == Resource.Status.ERROR) {
        emit(parseError.invoke(responseStatus))
    } else {
        emit(responseStatus)
    }

}

