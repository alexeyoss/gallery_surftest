package com.oss.gallery.feature_authorization.presentation.states

sealed interface AuthUiEvents {
    object Login : AuthUiEvents
    object CheckTokenStorage : AuthUiEvents
}