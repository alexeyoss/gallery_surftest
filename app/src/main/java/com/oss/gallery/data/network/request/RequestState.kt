package com.oss.gallery.data.network.request

import com.oss.gallery.data.network.response.NetworkErrorResponse

sealed interface RequestState<out T> {
    object Loading : RequestState<Nothing>
    data class Success<out T>(internal val data: T) : RequestState<T>
    data class Error<out T>(val error: T) : ErrorState
}

sealed interface ErrorState : RequestState<Nothing> {
    data class GenericError(val throwable: Throwable) : ErrorState
    data class ServerError(
        val code: Int? = null,
        val message: String? = null,
        val response: NetworkErrorResponse? = null
    ) : ErrorState

    object ConnectionError : ErrorState
    object UnauthorizedError : ErrorState
}
