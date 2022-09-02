package com.oss.gallery.ui.activities.auth_activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oss.gallery.data.network.request.NetworkAuthRequest
import com.oss.gallery.di.IoDispatcher
import com.oss.gallery.ui.interactor.InteractorImpl
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
    private val interactor: InteractorImpl,
    @IoDispatcher
    private val IoDispatcher: CoroutineDispatcher
) : ViewModel(), AuthViewModel {

    override val tokenState = MutableStateFlow<TokenState>(TokenState.Exist(""))

    override fun login(authRequest: NetworkAuthRequest) {
        viewModelScope.launch(IoDispatcher) {
            interactor.login(authRequest)
                .onEach {
                    when (it) {
                    }
                }.launchIn(viewModelScope)
        }
    }

    override fun checkTokenStatus() {
        viewModelScope.launch(IoDispatcher) {
            interactor.checkTokenStatus()
                .onEach {
                    when (it) {
                        is TokenState.Exist -> tokenState.emit(it)
                        else -> tokenState.emit(it)
                    }
                }.launchIn(viewModelScope)
        }
    }
}