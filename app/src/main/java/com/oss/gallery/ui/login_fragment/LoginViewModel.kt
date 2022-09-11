package com.oss.gallery.ui.login_fragment

import com.oss.gallery.data.network.request.NetworkAuthRequest
import com.oss.gallery.ui.states.AuthUiEvents
import com.oss.gallery.ui.states.AuthUiStates
import com.oss.gallery.ui.states.ValidationState
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface LoginViewModel {
    val authUiStateFlow: StateFlow<AuthUiStates>
    val authUiEventFlow: StateFlow<AuthUiEvents>

    val loginValidationFlow: StateFlow<ValidationState>
    val passwordValidationFlow: StateFlow<ValidationState>
    val commonValidationFlow: SharedFlow<Boolean>

    fun login(authRequest: NetworkAuthRequest)
    fun validateLoginForm(dataModel: NetworkAuthRequest)
}