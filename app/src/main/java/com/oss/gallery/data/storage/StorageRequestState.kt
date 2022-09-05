package com.oss.gallery.data.storage

sealed interface StorageRequestState<out T> {
    data class Success<out T>(internal val data: T) : StorageRequestState<T>
    object Error : StorageRequestState<Nothing>
}
