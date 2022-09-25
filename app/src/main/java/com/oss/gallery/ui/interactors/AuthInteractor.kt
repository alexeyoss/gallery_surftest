package com.oss.gallery.ui.interactors

import com.oss.gallery.data.network.request.NetworkAuthRequest
import com.oss.gallery.ui.states.auth_activity_states.AuthUiStates
import kotlinx.coroutines.flow.Flow

interface AuthInteractor : BaseInteractor {
    suspend fun login(authRequest: NetworkAuthRequest): Flow<AuthUiStates>
    suspend fun checkTokenStatus(): Flow<String>
    suspend fun getPictureFromTheNetwork(): Flow<AuthUiStates>
}