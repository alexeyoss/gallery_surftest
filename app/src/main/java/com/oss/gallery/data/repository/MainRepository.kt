package com.oss.gallery.data.repository

import com.oss.gallery.data.network.request.NetworkAuthRequest
import com.oss.gallery.data.network.request.NetworkRequestState
import com.oss.gallery.data.network.response.NetworkAuthResponse
import com.oss.gallery.data.network.response.NetworkPictureResponse
import com.oss.gallery.data.storage.StorageRequestState

interface MainRepository {
    suspend fun login(authRequest: NetworkAuthRequest): NetworkRequestState<NetworkAuthResponse>
    suspend fun logout()
    suspend fun getPicturesFromNetwork(): NetworkRequestState<NetworkPictureResponse>
    suspend fun checkTokenStatus(): StorageRequestState<String>
}