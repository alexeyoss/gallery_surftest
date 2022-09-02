package com.oss.gallery.data.storage

sealed interface StorageRequestState<out T> {
    object Loading : StorageRequestState<Nothing>
    data class Success<out T>(internal val data: T) : StorageRequestState<T>
    data class Error<out T>(val error: T) : StorageRequestState<Nothing>
}
