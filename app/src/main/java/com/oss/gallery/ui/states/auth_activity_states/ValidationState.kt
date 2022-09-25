package com.oss.gallery.ui.states.auth_activity_states

import androidx.annotation.StringRes

sealed interface ValidationState {
    object Successful : ValidationState
    data class EmptyFiledError(@StringRes val message: Int) : ValidationState
    data class IncorrectFiledError(@StringRes val message: Int) : ValidationState
    object Initial : ValidationState
}