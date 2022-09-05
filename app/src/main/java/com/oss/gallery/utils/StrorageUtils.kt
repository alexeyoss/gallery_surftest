package com.oss.gallery.utils

import android.util.Log
import com.oss.gallery.data.storage.StorageRequestState

suspend fun <T> safeStorageCall(
    storageCall: suspend () -> T
): StorageRequestState<T> = runCatching {
    StorageRequestState.Success(storageCall())
}.getOrElse {
    Log.w("WARN", "TokenStorage troubles - $it")
    StorageRequestState.Error
}