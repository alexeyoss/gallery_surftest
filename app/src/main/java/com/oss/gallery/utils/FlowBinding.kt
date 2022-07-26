package com.oss.gallery.utils

import android.os.Looper

private fun checkMainThread() {
    check(Looper.myLooper() == Looper.getMainLooper()) {
        "Expected to be called on the main thread but was " + Thread.currentThread().name
    }
}

//fun SearchView.textChanges(): Flow<String?> {
//    return callbackFlow {
//        checkMainThread()
//
//        val listener = object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                trySend(query)
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                trySend(newText)
//            }
//        }
//        awaitClose{
//
//        }
//    }
//}