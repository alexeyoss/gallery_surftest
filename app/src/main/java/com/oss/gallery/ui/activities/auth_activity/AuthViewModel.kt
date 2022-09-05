package com.oss.gallery.ui.activities.auth_activity

import com.oss.gallery.data.network.request.NetworkAuthRequest
import com.oss.gallery.ui.states.AuthUiStates
import com.oss.gallery.ui.states.TokenState
import kotlinx.coroutines.flow.MutableStateFlow

interface AuthViewModel {
    val tokenState: MutableStateFlow<TokenState>
    val authUiState: MutableStateFlow<AuthUiStates>

    fun login(authRequest: NetworkAuthRequest)
    fun checkTokenStatus()
    fun getPicturesFromNetwork(token: String)
}