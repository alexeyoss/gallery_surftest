package com.oss.gallery.feature_posts.domain.util

import com.oss.gallery.feature_posts.data.network.request.ErrorState
import com.oss.gallery.feature_posts.data.network.request.NetworkRequestState
import com.oss.gallery.feature_posts.presentation.states.MainUiStates
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <NetworkResponse> buildMainRequestFlow(
    block: suspend () -> NetworkRequestState<NetworkResponse>
): Flow<MainUiStates> {
    return flow {
        emit(MainUiStates.Loading)
        val result = when (val request = block()) {
            is NetworkRequestState.Success -> {
                MainUiStates.Success(request.data)
            }
            else -> MainUiStates.Error(request as ErrorState)
        }
        emit(result)
    }
}