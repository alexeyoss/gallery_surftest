package com.oss.gallery.data.repository

import com.oss.gallery.data.network.request.NetworkAuthRequest
import com.oss.gallery.data.network.request.NetworkRequestState
import com.oss.gallery.data.network.response.NetworkAuthResponse
import com.oss.gallery.data.network.response.NetworkPictureResponse

interface MainRepository {
    suspend fun login(authRequest: NetworkAuthRequest): NetworkRequestState<NetworkAuthResponse>
    suspend fun logout(): NetworkRequestState<Unit>
    suspend fun getPicturesFromNetwork(): NetworkRequestState<List<NetworkPictureResponse>>
    suspend fun getTokenFromStorage(): String
    suspend fun saveTokenIntoStorage(token: String): Boolean
    suspend fun cleanStorageResources(): Boolean
}