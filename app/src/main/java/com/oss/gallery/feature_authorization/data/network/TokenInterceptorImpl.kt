package com.oss.gallery.feature_authorization.data.network

import com.oss.gallery.feature_authorization.data.storage.TokenStorage
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptorImpl
@Inject
constructor(
    private val tokenStorage: TokenStorage
) : Interceptor {

    private var accessToken = ""

    override fun intercept(chain: Interceptor.Chain): Response {
        //TODO not sure about runBlocking
        runBlocking {
            accessToken = tokenStorage.getToken()
        }

        return chain.run {
            proceed(
                request()
                    .newBuilder()
                    .addHeader("Authorization", "Token $accessToken")
                    .build()
            )
        }
    }
}