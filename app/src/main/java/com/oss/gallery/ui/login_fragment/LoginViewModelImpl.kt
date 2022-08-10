package com.oss.gallery.ui.login_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oss.gallery.data.network.request.NetworkAuthRequest
import com.oss.gallery.di.IoDispatcher
import com.oss.gallery.ui.interactor.InteractorImpl
import com.oss.gallery.ui.states.AuthUiEvents
import com.oss.gallery.ui.states.AuthUiStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModelImpl
@Inject
constructor(
    private val interactor: InteractorImpl,
    @IoDispatcher
    private val IoDispatcher: CoroutineDispatcher
) : ViewModel(), LoginViewModel {

    override val authUiStateFlow = MutableStateFlow<AuthUiStates>(AuthUiStates.Empty)
    override val authUiEventFlow = MutableStateFlow<AuthUiEvents>(AuthUiEvents.Login)

    override fun login(authRequest: NetworkAuthRequest) {
        viewModelScope.launch(IoDispatcher) {
            interactor.login(authRequest)
                .onEach { authUiState ->
                    when (authUiState) {
                        is AuthUiStates.Empty -> {}
                        is AuthUiStates.Loading -> {}
                        is AuthUiStates.Success -> authUiStateFlow.emit(authUiState)
                        is AuthUiStates.Error -> {}
                    }
                }
                .launchIn(viewModelScope)
        }
    }
}