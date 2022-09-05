package com.oss.gallery.ui.interactors

import com.oss.gallery.data.network.request.NetworkAuthRequest
import com.oss.gallery.ui.states.AuthUiStates
import com.oss.gallery.ui.states.TokenState
import kotlinx.coroutines.flow.Flow

interface AuthInteractor : BaseInteractor {
    suspend fun login(authRequest: NetworkAuthRequest): Flow<AuthUiStates>
    suspend fun checkTokenStatus(): Flow<TokenState>
    suspend fun getPictureFromTheNetwork(token: String): Flow<AuthUiStates>
}