package com.oss.gallery.data.network

import com.oss.gallery.data.network.request.NetworkAuthRequest
import com.oss.gallery.data.network.response.NetworkAuthResponse
import com.oss.gallery.data.network.response.NetworkPictureResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("auth/login")
    suspend fun login(
        @Body authRequest: NetworkAuthRequest
    ): NetworkAuthResponse

    @POST("auth/logout")
    suspend fun logout(): Response<Unit>

    @GET("picture")
    suspend fun getPictures(): List<NetworkPictureResponse>
}