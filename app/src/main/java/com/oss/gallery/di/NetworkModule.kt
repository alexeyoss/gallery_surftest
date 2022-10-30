package com.oss.gallery.di

import android.util.Log
import com.oss.gallery.BuildConfig
import com.oss.gallery.feature_authorization.data.network.AuthApiService
import com.oss.gallery.feature_authorization.data.network.TokenInterceptorImpl
import com.oss.gallery.feature_authorization.data.storage.TokenStorage
import com.oss.gallery.feature_posts.data.network.MainApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideLoginInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor { message ->
            Log.d("OKHTTP", message)
        }.also { it.level = HttpLoggingInterceptor.Level.BODY }
    }

    @Singleton
    @CoroutinesModule.ApplicationScope
    @Provides
    fun provideTokenInterceptor(
        appScope: CoroutineScope,
        tokenStorage: TokenStorage
    ): TokenInterceptorImpl {
        return TokenInterceptorImpl(
            appScope,
            tokenStorage
        )
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: TokenInterceptorImpl
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .callTimeout(15, java.util.concurrent.TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideAuthApiService(retrofit: Retrofit): AuthApiService {
        return retrofit.create(AuthApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideMainApiService(retrofit: Retrofit): MainApiService {
        return retrofit.create(MainApiService::class.java)
    }
}