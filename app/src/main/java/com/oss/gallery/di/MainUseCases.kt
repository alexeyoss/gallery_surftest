package com.oss.gallery.di

import com.oss.gallery.feature_posts.domain.repository.MainRepository
import com.oss.gallery.feature_posts.domain.repository.PostsRepository
import com.oss.gallery.feature_posts.domain.use_case.CleanStorageResourcesUseCase
import com.oss.gallery.feature_posts.domain.use_case.DeleteLikedPostUseCase
import com.oss.gallery.feature_posts.domain.use_case.GetAllCachedPostsUseCase
import com.oss.gallery.feature_posts.domain.use_case.GetCachedPicturesFromDbWithNetworkCallUseCase
import com.oss.gallery.feature_posts.domain.use_case.GetFavoritesPostsUseCase
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
    fun provideGetPicturesFromNetworkAndMapToBaseModelUseCase(
        mainRepository: MainRepository,
        postsRepository: PostsRepository
    ): GetCachedPicturesFromDbWithNetworkCallUseCase {
        return GetCachedPicturesFromDbWithNetworkCallUseCase(
            mainRepository,
            postsRepository
        )
    }

    @Singleton
    @Provides
    fun provideUserLogoutUseCase(repository: MainRepository): UserLogoutUseCase {
        return UserLogoutUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideLikePostWithTimeStampUseCase(
        postsRepository: PostsRepository,
        getAllCachedPosts: GetAllCachedPostsUseCase
    ): LikePostWithTimeStampUseCase {
        return LikePostWithTimeStampUseCase(
            postsRepository,
            getAllCachedPosts
        )
    }

    @Singleton
    @Provides
    fun provideGetAllCachedPostsUseCase(repository: PostsRepository): GetAllCachedPostsUseCase {
        return GetAllCachedPostsUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetFavoritesPostsUseCase(repository: PostsRepository): GetFavoritesPostsUseCase {
        return GetFavoritesPostsUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideDeleteLikedPostUseCase(
        repository: PostsRepository,
        getFavoritesPosts: GetFavoritesPostsUseCase
    ): DeleteLikedPostUseCase {
        return DeleteLikedPostUseCase(
            repository,
            getFavoritesPosts
        )
    }

    @Singleton
    @Provides
    fun provideMainUseCases(
        logoutUseCase: UserLogoutUseCase,
        cleanStorageResourcesUseCase: CleanStorageResourcesUseCase,
        getPicturesFromNetworkAndMapToBaseModelUseCase: GetCachedPicturesFromDbWithNetworkCallUseCase,
        likePostWithTimeStampUseCase: LikePostWithTimeStampUseCase,
        getAllCachedPosts: GetAllCachedPostsUseCase,
        getFavoritesPosts: GetFavoritesPostsUseCase,
        deleteLikedPostUseCase: DeleteLikedPostUseCase
    ): MainUseCases {
        return MainUseCases(
            logoutUseCase,
            cleanStorageResourcesUseCase,
            getPicturesFromNetworkAndMapToBaseModelUseCase,
            likePostWithTimeStampUseCase,
            getAllCachedPosts,
            getFavoritesPosts,
            deleteLikedPostUseCase,
        )
    }
}