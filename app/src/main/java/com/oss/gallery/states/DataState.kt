package com.oss.gallery.states

sealed class DataState<out T> {
    object Loading : DataState<Nothing>()
    data class Success<T>(internal val data: T) : DataState<T>()
    data class Error<T>(internal val error: Throwable) : DataState<T>()
}