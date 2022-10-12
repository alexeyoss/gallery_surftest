package com.oss.gallery.di

import com.oss.gallery.feature_posts.domain.repository.MainRepository
import com.oss.gallery.feature_posts.domain.repository.PostsRepository
import com.oss.gallery.feature_posts.domain.use_case.CleanStorageResourcesUseCase
import com.oss.gallery.feature_posts.domain.use_case.GetPicturesFromNetworkAndMapToBaseModelUseCase
import com.oss.gallery.feature_posts.domain.use_case.LikePostWithTimeStampUseCase
import com.oss.gallery.feature_posts.domain.use_case.MainUseCases
import com.oss.gallery.feature_posts.domain.use_case.UserLogoutUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainUseCases {

    @Singleton
    @Provides
    fun provideCleanStorageResourcesUseCase(repository: MainRepository): CleanStorageResourcesUseCase {
        return CleanStorageResourcesUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetPicturesFromNetworkAndMapToBaseModelUseCase(repository: MainRepository): GetPicturesFromNetworkAndMapToBaseModelUseCase {
        return GetPicturesFromNetworkAndMapToBaseModelUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideUserLogoutUseCase(repository: MainRepository): UserLogoutUseCase {
        return UserLogoutUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideLikePostWithTimeStampUseCase(repository: PostsRepository): LikePostWithTimeStampUseCase {
        return LikePostWithTimeStampUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideMainUseCases(
        logoutUseCase: UserLogoutUseCase,
        cleanStorageResourcesUseCase: CleanStorageResourcesUseCase,
        getPicturesFromNetworkAndMapToBaseModelUseCase: GetPicturesFromNetworkAndMapToBaseModelUseCase,
        likePostWithTimeStampUseCase: LikePostWithTimeStampUseCase
    ): MainUseCases {
        return MainUseCases(
            logoutUseCase,
            cleanStorageResourcesUseCase,
            getPicturesFromNetworkAndMapToBaseModelUseCase,
            likePostWithTimeStampUseCase
        )
    }
}