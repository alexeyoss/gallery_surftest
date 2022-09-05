package com.oss.gallery.di

import com.oss.gallery.data.network.ApiService
import com.oss.gallery.data.repository.MainRepositoryImpl
import com.oss.gallery.data.storage.TokenStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        apiService: ApiService,
        tokenStorage: TokenStorage
    ): MainRepositoryImpl {
        return MainRepositoryImpl(
            apiService,
            tokenStorage
        )
    }
}