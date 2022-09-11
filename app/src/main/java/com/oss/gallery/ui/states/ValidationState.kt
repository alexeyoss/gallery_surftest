package com.oss.gallery.ui.states

sealed interface ValidationState {
    object Successful: ValidationState
    data class EmptyFiledError(val message: String) : ValidationState
    data class IncorrectFiledError(val message: String) : ValidationState
    object Initial : ValidationState
}