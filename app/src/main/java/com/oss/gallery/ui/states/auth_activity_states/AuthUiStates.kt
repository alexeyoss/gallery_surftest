package com.oss.gallery.ui.states.auth_activity_states

sealed interface AuthUiStates {
    data class Success<T>(internal val data: T) : AuthUiStates
    data class Error<T>(val error: T) : AuthUiStates
    object Loading : AuthUiStates
    object Empty : AuthUiStates
}