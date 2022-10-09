package com.oss.gallery.feature_authorization.presentation.states

import androidx.annotation.StringRes

sealed interface ValidationState {
    object Successful : ValidationState
    data class EmptyFiledError(@StringRes val message: Int) : ValidationState
    data class IncorrectFiledError(@StringRes val message: Int) : ValidationState
    object Initial : ValidationState
}