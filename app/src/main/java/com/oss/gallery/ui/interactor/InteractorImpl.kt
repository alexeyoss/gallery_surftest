package com.oss.gallery.ui.interactor

import com.oss.gallery.data.network.request.ErrorState
import com.oss.gallery.data.network.request.NetworkAuthRequest
import com.oss.gallery.data.network.request.RequestState
import com.oss.gallery.data.network.response.NetworkAuthResponse
import com.oss.gallery.data.repository.MainRepositoryImpl
import com.oss.gallery.ui.states.AuthUiStates
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class InteractorImpl constructor(
    private val mainRepository: MainRepositoryImpl
) : Interactor {
    suspend fun login(authRequest: NetworkAuthRequest): Flow<AuthUiStates> {
        return buildRequestFlow {
            mainRepository.login(authRequest)
        }
    }
}

private fun buildRequestFlow(
    requestFlow: suspend () -> RequestState<NetworkAuthResponse>
): Flow<AuthUiStates> {
    return flow {
        emit(AuthUiStates.Loading)
        val result = when (val requestState = requestFlow()) {
            is RequestState.Success -> AuthUiStates.Success
            else -> AuthUiStates.Error(requestState as ErrorState)
        }
        emit(result)
    }
}