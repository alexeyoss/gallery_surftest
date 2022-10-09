package com.oss.gallery.feature_authorization.presentation.login_fragment

import com.oss.gallery.feature_authorization.data.network.request.NetworkAuthRequest
import com.oss.gallery.feature_authorization.presentation.states.AuthUiEvents
import com.oss.gallery.feature_authorization.presentation.states.AuthUiStates
import com.oss.gallery.feature_authorization.presentation.states.ValidationState
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