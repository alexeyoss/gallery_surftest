package com.oss.gallery.network

import com.oss.gallery.network.response.AuthModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("/auth/login")
    suspend fun login(
        @Body authRequest: AuthRequest
    ): AuthModel

    @POST("/auth/logout")
    suspend fun logout()

    @GET("/picture")
    suspend fun getPictures(
        @Header("Authorization") token: String
    )
}