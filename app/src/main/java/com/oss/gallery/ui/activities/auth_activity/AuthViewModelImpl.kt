package com.oss.gallery.ui.activities.auth_activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oss.gallery.data.network.request.NetworkAuthRequest
import com.oss.gallery.di.IoDispatcher
import com.oss.gallery.ui.interactors.AuthInteractorImpl
import com.oss.gallery.ui.states.auth_activity_states.AuthUiStates
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
    private val interactor: AuthInteractorImpl,
    @IoDispatcher
    private val IoDispatcher: CoroutineDispatcher
) : ViewModel(), AuthViewModel {

    private val _tokenState = MutableStateFlow("")
    override val tokenState = _tokenState.asStateFlow()

    private val _authUiState = MutableStateFlow<AuthUiStates>(AuthUiStates.Empty)
    override val authUiState = _authUiState.asStateFlow()

    init {
//        checkTokenStatus()
    }

    override fun login(authRequest: NetworkAuthRequest) {
        viewModelScope.launch(IoDispatcher) {
            interactor.login(authRequest)
                .onEach { _authUiState.emit(it) }
                .launchIn(viewModelScope)
        }
    }

    override fun checkTokenStatus() {
        viewModelScope.launch(IoDispatcher) {
            interactor.checkTokenStatus()
                .onEach { _tokenState.emit(it) }
                .launchIn(viewModelScope)
        }
    }

    override fun getPicturesFromNetwork() {
        viewModelScope.launch(IoDispatcher) {
            interactor.getPictureFromTheNetwork()
                .onEach { _authUiState.emit(it) }
                .launchIn(viewModelScope)
        }
    }
}