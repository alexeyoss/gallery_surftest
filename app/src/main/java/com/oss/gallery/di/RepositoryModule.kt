package com.oss.gallery.di

import com.oss.gallery.data.repository.MainRepository
import com.oss.gallery.data.repository.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMainRepository(impl: MainRepositoryImpl): MainRepository
}