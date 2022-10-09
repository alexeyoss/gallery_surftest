package com.oss.gallery.feature_posts.data.network

import com.oss.gallery.feature_posts.data.network.response.NetworkPictureResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface MainApiService {

    @POST("auth/logout")
    suspend fun logout(): Response<Unit>

    @GET("picture")
    suspend fun getPictures(): List<NetworkPictureResponse>
}