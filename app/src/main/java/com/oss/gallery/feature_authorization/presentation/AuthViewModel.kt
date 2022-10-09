package com.oss.gallery.feature_authorization.presentation

import com.oss.gallery.feature_authorization.presentation.states.AuthUiStates
import kotlinx.coroutines.flow.StateFlow

interface AuthViewModel {
    val tokenState: StateFlow<String>
    val authUiState: StateFlow<AuthUiStates>

    fun getTokenFromStorage()
    fun checkTokenState()
}