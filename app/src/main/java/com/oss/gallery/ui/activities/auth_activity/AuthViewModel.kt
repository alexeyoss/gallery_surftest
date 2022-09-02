package com.oss.gallery.ui.activities.auth_activity

import com.oss.gallery.data.network.request.NetworkAuthRequest
import com.oss.gallery.ui.states.TokenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface AuthViewModel {
    val tokenState: MutableStateFlow<TokenState>

    fun login(authRequest: NetworkAuthRequest)
    fun checkTokenStatus()
}