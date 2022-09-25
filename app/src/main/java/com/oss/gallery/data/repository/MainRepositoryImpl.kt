package com.oss.gallery.data.repository

import com.oss.gallery.data.network.ApiService
import com.oss.gallery.data.network.request.NetworkAuthRequest
import com.oss.gallery.data.network.request.NetworkRequestState
import com.oss.gallery.data.network.response.NetworkAuthResponse
import com.oss.gallery.data.network.response.NetworkPictureResponse
import com.oss.gallery.data.storage.TokenStorage
import com.oss.gallery.utils.safeApiCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepositoryImpl
@Inject
constructor(
    private val apiService: ApiService,
    private val tokenStorage: TokenStorage
) : MainRepository {

    override suspend fun login(authRequest: NetworkAuthRequest): NetworkRequestState<NetworkAuthResponse> {
        return safeApiCall {
            apiService.login(authRequest)
                .also { networkAuthResponse ->
                    saveTokenIntoStorage(networkAuthResponse.token)
                }
        }
    }

    override suspend fun logout(): NetworkRequestState<Unit> {
        return safeApiCall {
            apiService.logout()
        }
    }

    override suspend fun getPicturesFromNetwork(): NetworkRequestState<NetworkPictureResponse> {
        return safeApiCall {
            apiService.getPictures()
        }
    }

    override suspend fun getTokenFromStorage(): String = tokenStorage.getToken()

    override suspend fun saveTokenIntoStorage(token: String): Boolean =
        tokenStorage.saveToken(token)

    override suspend fun cleanStorageResources(): Boolean = tokenStorage.deleteToken()
}