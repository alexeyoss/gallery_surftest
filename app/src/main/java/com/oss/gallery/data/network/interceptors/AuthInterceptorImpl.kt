package com.oss.gallery.data.network.interceptors

import com.oss.gallery.data.storage.TokenStorage
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptorImpl
@Inject
constructor(
    private val tokenStorage: TokenStorage
) : Interceptor {

    private var accessToken = ""

    override fun intercept(chain: Interceptor.Chain): Response {
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