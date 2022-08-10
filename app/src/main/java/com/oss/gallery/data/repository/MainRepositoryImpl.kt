package com.oss.gallery.data.repository

import com.oss.gallery.data.network.ApiService
import com.oss.gallery.data.network.request.NetworkAuthRequest
import com.oss.gallery.data.network.request.RequestState
import com.oss.gallery.data.network.response.NetworkAuthResponse
import com.oss.gallery.utils.safeApiCall

class MainRepositoryImpl
constructor(
    private val apiService: ApiService,
    private val baseModelMapper: BasePictureModelMapper
) : MainRepository {

    private lateinit var result: NetworkAuthResponse

    override suspend fun login(authRequest: NetworkAuthRequest): RequestState<NetworkAuthResponse> {
        return safeApiCall {
            apiService.login(authRequest).also { result = it }
        }
    }

    override suspend fun logout() {
    }

    override suspend fun getPictures() {
        // TODO mapping to the BasePictureModel
    }
}