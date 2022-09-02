package com.oss.gallery.utils

import android.util.Log
import com.oss.gallery.data.storage.StorageRequestState

suspend fun <T> safeStorageCall(
    call: suspend () -> T
): StorageRequestState<T> = runCatching {
    StorageRequestState.Success(
        call()
    )
}.getOrElse {
    Log.w("WARN", "TokenStorage troubles - $it")
    StorageRequestState.Error("")
}