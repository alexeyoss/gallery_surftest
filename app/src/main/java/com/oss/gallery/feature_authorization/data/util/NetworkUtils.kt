package com.oss.gallery.feature_posts.utils

import android.util.Log
import com.google.gson.GsonBuilder
import com.oss.gallery.feature_authorization.data.network.response.NetworkErrorResponse
import com.oss.gallery.feature_authorization.data.network.ErrorState
import com.oss.gallery.feature_authorization.data.network.NetworkRequestState
import retrofit2.HttpException
import java.net.UnknownHostException

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): NetworkRequestState<T> = runCatching {
    NetworkRequestState.Success(apiCall())
}.getOrElse {
    Log.w("WARN", "Network api call troubles - $it")
    checkThrowable(it)
}

private fun checkThrowable(throwable: Throwable): NetworkRequestState<Nothing> = when (throwable) {
    is HttpException -> parseHttpException(throwable)
    is UnknownHostException -> ErrorState.ConnectionError
    else -> ErrorState.GenericError(throwable)
}

private fun parseHttpException(exception: HttpException): NetworkRequestState<Nothing> {
    return runCatching {
        val response = exception.response()?.let {
            GsonBuilder()
                .create()
                .fromJson(it.body().toString(), NetworkErrorResponse::class.java)
        }
        if (response != null) {
            ErrorState.ServerError(
                exception.code(),
                exception.message(),
                response
            )
        } else {
            ErrorState.ServerError()
        }
    }.getOrElse {
        ErrorState.GenericError(it)
    }
}



