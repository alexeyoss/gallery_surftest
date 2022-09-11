package com.oss.gallery.utils

import android.os.Looper
import android.widget.TextView
import androidx.annotation.CheckResult
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.santalu.maskara.widget.MaskEditText
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

fun <T> Flow<T>.collectOnLifecycle(
    lifecycleOwner: AppCompatActivity,
    collector: (T) -> Unit
) {
    collectOnLifecycle(lifecycleOwner, Lifecycle.State.STARTED, collector)
}

fun <T> Flow<T>.collectOnLifecycle(
    lifecycleOwner: Fragment,
    collector: (T) -> Unit
) {
    collectOnLifecycle(lifecycleOwner, Lifecycle.State.RESUMED, collector)
}

fun <T> Flow<T>.collectOnLifecycle(
    lifecycleOwner: LifecycleOwner,
    state: Lifecycle.State,
    collector: (T) -> Unit
) {
    lifecycleOwner.lifecycleScope.launch {
        lifecycleOwner.repeatOnLifecycle(state) {
            collect {
                collector(it)
            }
        }
    }
}

private fun checkMainThread() {
    check(Looper.myLooper() == Looper.getMainLooper()) {
        "Expected to be called on the main thread but was " + Thread.currentThread().name
    }
}

@ExperimentalCoroutinesApi
@CheckResult
// TODO get rid of Mask Edit Text refactor
fun MaskEditText.textChanges(): Flow<CharSequence?> {
    return callbackFlow {
        checkMainThread()

        val listener = doOnTextChanged { text, _, _, _ -> trySend(text) }

        addTextChangedListener(listener)
        awaitClose { removeTextChangedListener(listener) }
    }.onStart { emit(text) }
}

@ExperimentalCoroutinesApi
@CheckResult
fun TextView.textChanges(): Flow<CharSequence?> {
    return callbackFlow {
        checkMainThread()

        val listener = doOnTextChanged { text, _, _, _ -> trySend(text) }

        addTextChangedListener(listener)
        awaitClose { removeTextChangedListener(listener) }
    }.onStart { emit(text) }
}
