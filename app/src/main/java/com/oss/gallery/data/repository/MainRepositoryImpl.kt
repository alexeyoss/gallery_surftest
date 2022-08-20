package com.oss.gallery.data.repository

import com.oss.gallery.data.network.ApiService
import com.oss.gallery.data.network.request.NetworkAuthRequest
import com.oss.gallery.data.network.request.RequestState
import com.oss.gallery.data.network.response.NetworkAuthResponse
import com.oss.gallery.data.network.response.NetworkPictureResponse
import com.oss.gallery.data.storage.TokenStorage
import com.oss.gallery.utils.safeApiCall

class MainRepositoryImpl
constructor(
    private val apiService: ApiService,
    private val tokenStorage: TokenStorage,
    private val baseModelMapper: BasePictureModelMapper
) : MainRepository {

    private lateinit var result: NetworkAuthResponse

    override suspend fun login(authRequest: NetworkAuthRequest): RequestState<NetworkAuthResponse> {
        return safeApiCall {
            apiService.login(authRequest).also {
                result = it
                tokenStorage.saveToken(result.token)
            }
        }
    }

    override suspend fun logout() {
        apiService.logout(result.token)
    }

    override suspend fun getPicturesFromNetwork(): RequestState<NetworkPictureResponse> {
        return safeApiCall {
            apiService.getPictures(result.token)
        }
    }
}