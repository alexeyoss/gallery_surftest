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

class MainRepositoryImpl
@Inject
constructor(
    private val apiService: ApiService,
    private val tokenStorage: TokenStorage
) : MainRepository {

    override suspend fun login(authRequest: NetworkAuthRequest): NetworkRequestState<NetworkAuthResponse> {
        return safeApiCall {
            apiService.login(authRequest)
            // TODO safeToken
//                .also { networkAuthResponse ->
//                saveTokenIntoStorage(networkAuthResponse.token)
//            }
        }
    }

    override suspend fun logout(token: String) {
        apiService.logout(token)
    }

    override suspend fun getPicturesFromNetwork(token: String): NetworkRequestState<NetworkPictureResponse> {
        return safeApiCall {
            apiService.getPictures(token)
        }
    }

    // TODO  implement the lifetime value of token on the server
    override suspend fun getTokenFromStorage(): StorageRequestState<String> {
        return safeStorageCall {
            tokenStorage.getToken()
        }
    }

    override suspend fun saveTokenIntoStorage(token: String): StorageRequestState<Boolean> {
        return safeStorageCall {
            tokenStorage.saveToken(token)
        }
    }
}