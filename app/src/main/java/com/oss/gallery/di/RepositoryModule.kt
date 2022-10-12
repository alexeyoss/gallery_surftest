package com.oss.gallery.di

import com.oss.gallery.feature_authorization.data.repository.AuthRepositoryImpl
import com.oss.gallery.feature_authorization.domain.repository.AuthRepository
import com.oss.gallery.feature_posts.data.repository.MainRepositoryImpl
import com.oss.gallery.feature_posts.data.repository.PostsRepositoryImpl
import com.oss.gallery.feature_posts.domain.repository.MainRepository
import com.oss.gallery.feature_posts.domain.repository.PostsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    abstract fun bindMainRepository(impl: MainRepositoryImpl): MainRepository

    @Binds
    abstract fun bindPostsRepository(impl: PostsRepositoryImpl): PostsRepository
}