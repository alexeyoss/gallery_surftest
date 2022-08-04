package com.oss.gallery.data.network

import com.oss.gallery.data.network.request.NetworkAuthRequest
import com.oss.gallery.data.network.response.NetworkAuthResponse
import com.oss.gallery.data.network.response.NetworkPictureResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("/auth/login")
    suspend fun login(
        @Body authRequest: NetworkAuthRequest
    ): NetworkAuthResponse

    @POST("/auth/logout")
    suspend fun logout(
        @Header("Authorization") token: String
    )

    @GET("/picture")
    suspend fun getPictures(
        @Header("Authorization") token: String
    ): NetworkPictureResponse
}