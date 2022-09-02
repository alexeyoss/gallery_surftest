package com.oss.gallery.ui.states

import com.oss.gallery.data.network.request.ErrorState
import com.oss.gallery.data.network.response.NetworkAuthResponse

sealed interface AuthUiStates {
    data class Success(internal val networkAuthResponse: NetworkAuthResponse) : AuthUiStates
    data class Error(val error: ErrorState) : AuthUiStates
    object Loading : AuthUiStates
    object Empty : AuthUiStates
}
