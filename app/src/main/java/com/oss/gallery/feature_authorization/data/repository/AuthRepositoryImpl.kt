package com.oss.gallery.feature_authorization.data.repository

import com.oss.gallery.feature_authorization.data.network.AuthApiService
import com.oss.gallery.feature_authorization.data.network.request.NetworkAuthRequest
import com.oss.gallery.feature_authorization.data.network.response.NetworkAuthResponse
import com.oss.gallery.feature_authorization.data.storage.TokenStorage
import com.oss.gallery.feature_authorization.domain.repository.AuthRepository
import com.oss.gallery.feature_posts.data.network.request.NetworkRequestState
import com.oss.gallery.feature_posts.data.network.response.NetworkPictureResponse
import com.oss.gallery.feature_posts.utils.safeApiCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl
@Inject
constructor(
    private val authApiService: AuthApiService,
    private val tokenStorage: TokenStorage,
) : AuthRepository {
    override suspend fun login(authRequest: NetworkAuthRequest): NetworkRequestState<NetworkAuthResponse> {
        return safeApiCall {
            authApiService.login(authRequest)
                .also { networkAuthResponse ->
                    saveTokenIntoStorage(networkAuthResponse.token) // TODO refactoring, save whole information to DB
                }
        }
    }

    override suspend fun checkTokenState(): NetworkRequestState<List<NetworkPictureResponse>> {
        return safeApiCall {
            authApiService.getPictures() // TODO use another method form own server
        }
    }

    override suspend fun getTokenFromStorage(): String = tokenStorage.getToken()

    override suspend fun saveTokenIntoStorage(token: String): Boolean {
        return tokenStorage.saveToken(token)
    }
}