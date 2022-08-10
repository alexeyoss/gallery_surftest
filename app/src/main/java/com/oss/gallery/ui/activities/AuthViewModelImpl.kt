package com.oss.gallery.ui.activities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oss.gallery.data.network.request.NetworkAuthRequest
import com.oss.gallery.di.IoDispatcher
import com.oss.gallery.ui.interactor.InteractorImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
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

    override fun login(authRequest: NetworkAuthRequest) {
        viewModelScope.launch(IoDispatcher) {
            interactor.login(authRequest)
                .onEach {
                    when (it) {
                    }
                }.launchIn(viewModelScope)
        }
    }
}