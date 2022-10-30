package com.oss.gallery.feature_authorization.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oss.gallery.di.CoroutinesModule
import com.oss.gallery.feature_authorization.domain.use_case.AuthUseCases
import com.oss.gallery.feature_authorization.presentation.states.AuthUiStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModelImpl
@Inject
constructor(
    private val authUseCases: AuthUseCases,
    @CoroutinesModule.IoDispatcher
    private val IoDispatcher: CoroutineDispatcher
) : ViewModel(), AuthViewModel {

    private val _tokenState = MutableStateFlow("")
    override val tokenState = _tokenState.asStateFlow()

    private val _authUiState = MutableStateFlow<AuthUiStates>(AuthUiStates.Empty)
    override val authUiState = _authUiState.asStateFlow()

    init {
        getTokenFromStorage()
    }

    override fun getTokenFromStorage() {
        viewModelScope.launch(IoDispatcher) {
            authUseCases.getTokenFromStorage()
                .onEach { _tokenState.emit(it) }
                .launchIn(viewModelScope)
        }
    }

    override fun checkTokenState() {
        viewModelScope.launch(IoDispatcher) {
            authUseCases.checkTokenStatus()
                .onEach { _authUiState.emit(it) }
                .launchIn(viewModelScope)
        }
    }
}