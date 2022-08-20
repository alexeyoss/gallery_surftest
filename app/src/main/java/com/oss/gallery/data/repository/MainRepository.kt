package com.oss.gallery.data.repository

import com.oss.gallery.data.network.request.NetworkAuthRequest
import com.oss.gallery.data.network.request.RequestState
import com.oss.gallery.data.network.response.NetworkAuthResponse
import com.oss.gallery.data.network.response.NetworkPictureResponse

interface MainRepository {
    suspend fun login(authRequest: NetworkAuthRequest): RequestState<NetworkAuthResponse>
    suspend fun logout()
    suspend fun getPicturesFromNetwork(): RequestState<NetworkPictureResponse>
}