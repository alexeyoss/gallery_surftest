package com.oss.gallery.ui.interactor

import com.oss.gallery.data.network.request.ErrorState
import com.oss.gallery.data.network.request.NetworkAuthRequest
import com.oss.gallery.data.network.request.NetworkRequestState
import com.oss.gallery.data.network.response.NetworkAuthResponse
import com.oss.gallery.data.repository.MainRepositoryImpl
import com.oss.gallery.data.storage.StorageRequestState
import com.oss.gallery.ui.states.AuthUiStates
import com.oss.gallery.ui.states.TokenState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

// TODO base interface
class InteractorImpl constructor(
    private val mainRepository: MainRepositoryImpl
) : Interactor {
    suspend fun login(authRequest: NetworkAuthRequest): Flow<AuthUiStates> {
        return buildRequestFlow {
            mainRepository.login(authRequest)
        }
    }

    suspend fun checkTokenStatus(): Flow<TokenState> {
        return flow {
            val result = when (val requestState = mainRepository.checkTokenStatus()) {
                is StorageRequestState.Success -> TokenState.Exist(requestState.data)
                else -> TokenState.Empty("")
            }
            emit(result)
        }
    }
}

private fun buildRequestFlow(
    entryFlow: suspend () -> NetworkRequestState<NetworkAuthResponse>
): Flow<AuthUiStates> {
    return flow {
        emit(AuthUiStates.Loading)
        val result = when (val requestState = entryFlow()) {
            is NetworkRequestState.Success -> AuthUiStates.Success(requestState.data)
            else -> AuthUiStates.Error(requestState as ErrorState)
        }
        emit(result)
    }
}