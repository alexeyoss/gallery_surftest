package com.oss.gallery.ui.states

sealed interface AuthUiState {
    data class Success(val value: Boolean) : AuthUiState
    object Error : AuthUiState
    object Loading : AuthUiState
}
