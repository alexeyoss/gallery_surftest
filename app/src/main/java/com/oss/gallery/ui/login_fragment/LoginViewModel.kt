package com.oss.gallery.ui.login_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oss.gallery.data.network.request.NetworkAuthRequest
import com.oss.gallery.data.network.request.RequestState
import com.oss.gallery.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject
constructor(
//    private val repository: MainRepositoryImpl,
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _authResponse: MutableLiveData<RequestState<Any?>> =
        MutableLiveData()
    val dataState: LiveData<RequestState<Any?>> get() = _authResponse

    fun login(authRequest: NetworkAuthRequest) {
//        viewModelScope.launch(ioDispatcher) {
//            repository.login(authRequest)
//                .onEach { item ->
//                    when(item) {
//                        is
//                    }
//                    _authResponse.value = item
//                }
//                .launchIn(viewModelScope)
//        }
    }
}