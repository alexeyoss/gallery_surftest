package com.oss.gallery.ui.states.auth_activity_states

sealed interface AuthUiEvents {
    object Login : AuthUiEvents
    object CheckTokenStorage : AuthUiEvents
}