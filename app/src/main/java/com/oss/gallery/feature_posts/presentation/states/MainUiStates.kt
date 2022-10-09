package com.oss.gallery.feature_posts.presentation.states

sealed interface MainUiStates {
    data class Success<T>(internal val data: T) : MainUiStates
    data class Error<T>(val error: T) : MainUiStates
    object Loading : MainUiStates
    object Empty : MainUiStates
}