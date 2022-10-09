package com.oss.gallery.di

import com.oss.gallery.feature_authorization.domain.repository.AuthRepository
import com.oss.gallery.feature_authorization.domain.use_case.AuthUseCases
import com.oss.gallery.feature_authorization.domain.use_case.CheckTokenStatusUseCase
import com.oss.gallery.feature_authorization.domain.use_case.GetTokenFromStorageUseCase
import com.oss.gallery.feature_authorization.domain.use_case.SaveTokenIntoStorageUseCase
import com.oss.gallery.feature_authorization.domain.use_case.UserLoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthUseCases {

    @Singleton
    @Provides
    fun provideCheckTokenStatusUseCase(repository: AuthRepository): CheckTokenStatusUseCase {
        return CheckTokenStatusUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetTokenFromStorageUseCase(repository: AuthRepository): GetTokenFromStorageUseCase {
        return GetTokenFromStorageUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideSaveTokenIntoStorageUseCase(repository: AuthRepository): SaveTokenIntoStorageUseCase {
        return SaveTokenIntoStorageUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideUserLoginUseCase(repository: AuthRepository): UserLoginUseCase {
        return UserLoginUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideAuthUseCases(
        userLoginUseCase: UserLoginUseCase,
        getTokenFromStorageUseCase: GetTokenFromStorageUseCase,
        saveTokenIntoStorageUseCase: SaveTokenIntoStorageUseCase,
        checkTokenStatusUseCase: CheckTokenStatusUseCase
    ): AuthUseCases {
        return AuthUseCases(
            userLoginUseCase,
            checkTokenStatusUseCase,
            getTokenFromStorageUseCase,
            saveTokenIntoStorageUseCase
        )
    }
}