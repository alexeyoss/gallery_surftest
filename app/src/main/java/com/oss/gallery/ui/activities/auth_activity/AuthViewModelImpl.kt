package com.oss.gallery.ui.activities.auth_activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oss.gallery.data.network.request.NetworkAuthRequest
import com.oss.gallery.di.IoDispatcher
import com.oss.gallery.ui.interactors.AuthInteractorImpl
import com.oss.gallery.ui.states.AuthUiStates
import com.oss.gallery.ui.states.TokenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
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

    override val tokenState = MutableStateFlow<TokenState>(TokenState.Empty)
    override val authUiState = MutableStateFlow<AuthUiStates>(AuthUiStates.Empty)

    override fun login(authRequest: NetworkAuthRequest) {
        viewModelScope.launch(IoDispatcher) {
            interactor.login(authRequest)
                .onEach { authUiState.emit(it) }
                .launchIn(viewModelScope)
        }
    }

    override fun checkTokenStatus() {
        viewModelScope.launch(IoDispatcher) {
            interactor.checkTokenStatus()
                .onEach { tokenState.emit(it) }
                .launchIn(viewModelScope)
        }
    }

    override fun getPicturesFromNetwork(token: String) {
        viewModelScope.launch(IoDispatcher) {
            interactor.getPictureFromTheNetwork(token)
                .onEach { authUiState.emit(it) }
                .launchIn(viewModelScope)
        }
    }
}