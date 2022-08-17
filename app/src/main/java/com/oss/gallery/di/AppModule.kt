package com.oss.gallery.di

import android.content.Context
import com.oss.gallery.GalleryApp
import com.oss.gallery.data.storage.TokenStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): GalleryApp = app as GalleryApp

    @Singleton
    @Provides
    fun provideTokenStorage(
        @ApplicationContext app: Context,
        @IoDispatcher IoDispatcher: CoroutineDispatcher
    ): TokenStorage = TokenStorage(app, IoDispatcher)
}