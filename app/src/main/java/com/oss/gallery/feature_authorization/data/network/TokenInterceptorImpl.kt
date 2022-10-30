package com.oss.gallery.feature_authorization.data.network

import com.oss.gallery.di.CoroutinesModule
import com.oss.gallery.feature_authorization.data.storage.TokenStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenInterceptorImpl
@Inject
constructor(
    @CoroutinesModule.ApplicationScope private val appScope: CoroutineScope,
    private val tokenStorage: TokenStorage
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.run {
            proceed(
                request()
                    .newBuilder()
                    .addHeader(
                        "Authorization", "Token ${getAccessTokenJob()}"
                    )
                    .build()
            )
        }
    }

    private fun getAccessTokenJob(): String {
        return runBlocking {
            withContext(appScope.coroutineContext) { tokenStorage.getToken() }
        }
    }
}