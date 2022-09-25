package com.oss.gallery.ui.activities.auth_activity

import com.oss.gallery.data.network.request.NetworkAuthRequest
import com.oss.gallery.ui.states.auth_activity_states.AuthUiStates
import kotlinx.coroutines.flow.StateFlow

interface AuthViewModel {
    val tokenState: StateFlow<String>
    val authUiState: StateFlow<AuthUiStates>

    fun login(authRequest: NetworkAuthRequest)
    fun checkTokenStatus()
    fun getPicturesFromNetwork()
}