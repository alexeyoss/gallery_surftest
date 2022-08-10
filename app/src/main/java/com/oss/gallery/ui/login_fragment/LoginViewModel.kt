package com.oss.gallery.ui.login_fragment

import com.oss.gallery.data.network.request.NetworkAuthRequest
import com.oss.gallery.ui.states.AuthUiEvents
import com.oss.gallery.ui.states.AuthUiStates
import kotlinx.coroutines.flow.StateFlow

interface LoginViewModel {
    val authUiStateFlow: StateFlow<AuthUiStates>
    val authUiEventFlow: StateFlow<AuthUiEvents>

    fun login(authRequest: NetworkAuthRequest)
}