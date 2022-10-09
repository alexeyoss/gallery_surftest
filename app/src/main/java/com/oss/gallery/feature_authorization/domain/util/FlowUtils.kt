package com.oss.gallery.feature_authorization.domain.util

import com.oss.gallery.feature_authorization.presentation.states.AuthUiStates
import com.oss.gallery.feature_posts.data.network.request.ErrorState
import com.oss.gallery.feature_posts.data.network.request.NetworkRequestState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <NetworkResponse> buildAuthRequestFlow(
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