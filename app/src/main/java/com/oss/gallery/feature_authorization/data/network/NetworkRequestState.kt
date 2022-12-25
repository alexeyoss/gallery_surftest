package com.oss.gallery.feature_authorization.data.network

import com.oss.gallery.feature_authorization.data.network.response.NetworkErrorResponse

sealed interface NetworkRequestState<out T> {
    object Loading : NetworkRequestState<Nothing>
    data class Success<out T>(internal val data: T) : NetworkRequestState<T>
    data class Error<out T>(val error: T) : ErrorState
}

sealed interface ErrorState : NetworkRequestState<Nothing> {
    data class GenericError(val throwable: Throwable) : ErrorState
    data class ServerError(
        val code: Int? = null,
        val message: String? = null,
        val response: NetworkErrorResponse? = null
    ) : ErrorState

    object ConnectionError : ErrorState
}
