package com.oss.gallery.data.repository

import com.oss.gallery.data.network.request.NetworkAuthRequest
import com.oss.gallery.data.network.request.NetworkRequestState
import com.oss.gallery.data.network.response.NetworkAuthResponse
import com.oss.gallery.data.network.response.NetworkPictureResponse
import com.oss.gallery.data.storage.StorageRequestState

interface MainRepository {
    suspend fun login(authRequest: NetworkAuthRequest): NetworkRequestState<NetworkAuthResponse>
    suspend fun logout(token: String)
    suspend fun getPicturesFromNetwork(token: String): NetworkRequestState<NetworkPictureResponse>
    suspend fun getTokenFromStorage(): StorageRequestState<String>
    suspend fun saveTokenIntoStorage(token: String): StorageRequestState<Boolean>
}