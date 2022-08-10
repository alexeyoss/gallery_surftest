package com.oss.gallery.ui.states

import com.oss.gallery.data.network.request.ErrorState

sealed interface AuthUiStates {
    object Success: AuthUiStates
    data class Error(val error: ErrorState) : AuthUiStates
    object Loading : AuthUiStates
    object Empty : AuthUiStates
}
