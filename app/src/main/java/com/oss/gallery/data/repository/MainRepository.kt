package com.oss.gallery.data.repository

import com.oss.gallery.data.network.request.NetworkAuthRequest
import com.oss.gallery.data.network.request.RequestState
import com.oss.gallery.data.network.response.NetworkAuthResponse

interface MainRepository {
    suspend fun login(authRequest: NetworkAuthRequest): RequestState<NetworkAuthResponse>
    suspend fun logout()
    suspend fun getPictures()
//    suspend fun checkLoginStatus(searchData: String): RequestState<NetworkPictureResponse>
}