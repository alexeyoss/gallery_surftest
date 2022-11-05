package com.oss.gallery.feature_authorization.presentation

import androidx.lifecycle.ViewModel
import com.oss.gallery.feature_authorization.domain.use_case.AuthUseCases
import com.oss.gallery.feature_authorization.presentation.states.AuthUiEvents
import com.oss.gallery.feature_authorization.presentation.states.AuthUiStates
import com.oss.gallery.feature_posts.utils.launchWithIO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AuthViewModelImpl
@Inject
constructor(
    private val authUseCases: AuthUseCases,
) : ViewModel(), AuthViewModel {

    private val _tokenState = MutableStateFlow("")
    override val tokenState = _tokenState.asStateFlow()

    private val _authUiState = MutableStateFlow<AuthUiStates>(AuthUiStates.Empty)
    override val authUiState = _authUiState.asStateFlow()

    init {
        getTokenFromStorage()
    }

    override fun getTokenFromStorage() {
        launchWithIO<String, AuthUiEvents>(
            useCase = { authUseCases.getTokenFromStorage() },
            stateFlow = _tokenState
        )
    }

    override fun checkTokenState() {

        launchWithIO<AuthUiStates, AuthUiEvents>(
            useCase = { authUseCases.checkTokenStatus() },
            stateFlow = _authUiState
        )
    }
}