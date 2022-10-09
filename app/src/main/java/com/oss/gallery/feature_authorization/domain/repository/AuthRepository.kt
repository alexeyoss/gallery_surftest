package com.oss.gallery.feature_authorization.domain.repository

import com.oss.gallery.feature_authorization.data.network.request.NetworkAuthRequest
import com.oss.gallery.feature_authorization.data.network.response.NetworkAuthResponse
import com.oss.gallery.feature_posts.data.network.request.NetworkRequestState
import com.oss.gallery.feature_posts.data.network.response.NetworkPictureResponse

interface AuthRepository {
    suspend fun login(authRequest: NetworkAuthRequest): NetworkRequestState<NetworkAuthResponse>
    suspend fun checkTokenState(): NetworkRequestState<List<NetworkPictureResponse>>
    suspend fun getTokenFromStorage(): String
    suspend fun saveTokenIntoStorage(token: String): Boolean
}