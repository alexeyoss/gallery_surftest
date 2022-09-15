package com.oss.gallery.ui.interactors

import com.oss.gallery.data.network.request.ErrorState
import com.oss.gallery.data.network.request.NetworkAuthRequest
import com.oss.gallery.data.network.request.NetworkRequestState
import com.oss.gallery.data.repository.MainRepositoryImpl
import com.oss.gallery.data.storage.StorageRequestState
import com.oss.gallery.ui.states.auth_activity_states.AuthUiStates
import com.oss.gallery.ui.states.TokenState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthInteractorImpl
@Inject
constructor(
    private val mainRepository: MainRepositoryImpl
) : AuthInteractor {

    override suspend fun login(authRequest: NetworkAuthRequest): Flow<AuthUiStates> {
        // TODO Validation before the using the API  method
        return buildRequestFlow {
            mainRepository.login(authRequest)
        }
    }

    override suspend fun checkTokenStatus(): Flow<TokenState> {
        return flow {
            val result = when (val requestedData = mainRepository.getTokenFromStorage()) {
                is StorageRequestState.Success -> TokenState.Exist(requestedData.data)
                else -> TokenState.Empty
            }
            emit(result)
        }
    }

    override suspend fun getPictureFromTheNetwork(token: String): Flow<AuthUiStates> {
        return buildRequestFlow {
            mainRepository.getPicturesFromNetwork(token)
        }
    }

    private fun <NetworkResponse> buildRequestFlow(
        entryFlow: suspend () -> NetworkRequestState<NetworkResponse>
    ): Flow<AuthUiStates> {
        return flow {
            emit(AuthUiStates.Loading)
            val result = when (val requestState = entryFlow()) {
                is NetworkRequestState.Success -> {
                    AuthUiStates.Success(requestState.data)
                }
                else -> AuthUiStates.Error(requestState as ErrorState)
            }
            emit(result)
        }
    }
}
