package com.oss.gallery.ui.states.main_activity_states

sealed interface MainUiStates {
    data class Success<T>(internal val data: T) : MainUiStates
    data class Error<T>(val error: T) : MainUiStates
    object Loading : MainUiStates
    object Empty : MainUiStates
}