package com.oss.gallery.data.repository

import com.oss.gallery.data.network.ApiService
import com.oss.gallery.data.network.request.NetworkAuthRequest
import com.oss.gallery.data.network.request.NetworkRequestState
import com.oss.gallery.data.network.response.NetworkAuthResponse
import com.oss.gallery.data.network.response.NetworkPictureResponse
import com.oss.gallery.data.storage.StorageRequestState
import com.oss.gallery.data.storage.TokenStorage
import com.oss.gallery.utils.safeApiCall
import com.oss.gallery.utils.safeStorageCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton // TODO debatable ?
class MainRepositoryImpl
@Inject
constructor(
    private val apiService: ApiService,
    private val tokenStorage: TokenStorage,
    private val baseModelMapper: BasePictureModelMapper
) : MainRepository {

    private lateinit var result: NetworkAuthResponse

    override suspend fun login(authRequest: NetworkAuthRequest): NetworkRequestState<NetworkAuthResponse> {
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

    override suspend fun getPicturesFromNetwork(): NetworkRequestState<NetworkPictureResponse> {
        return safeApiCall {
            apiService.getPictures(result.token)
        }
    }

    override suspend fun checkTokenStatus(): StorageRequestState<String> {
        return safeStorageCall {
            tokenStorage.getToken() // TODO Checking the token status on the server
            // TODO  implement the lifetime value of token on the server
        }
    }
}