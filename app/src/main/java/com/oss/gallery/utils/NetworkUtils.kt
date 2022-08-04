package com.oss.gallery.utils

import com.oss.gallery.data.network.request.RequestState
import timber.log.Timber

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): RequestState<T> = runCatching {
    RequestState.Success(apiCall())
}.getOrElse {
    Timber.w(it)
    // TODO handle the Throwable
} as RequestState<T>

//
//private fun checkThrowable(throwable: Throwable): RequestState<Nothing> = when (throwable) {
//    is HttpException -> parseErrorResponse(throwable)
//    is UnknownHostException -> ErrorState.ConnectionError
//    else -> ErrorState.GenericError(throwable)
//}