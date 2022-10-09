package com.oss.gallery.di

import android.content.Context
import com.oss.gallery.App
import com.oss.gallery.feature_authorization.data.storage.TokenStorage
import com.oss.gallery.feature_authorization.presentation.util.LoginValidator
import com.oss.gallery.feature_authorization.presentation.util.PasswordValidator
import com.oss.gallery.feature_posts.data.repository.BasePictureModelMapper
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
    fun provideApplication(@ApplicationContext app: Context): App = app as App

    @Singleton
    @Provides
    fun provideTokenStorage(
        @ApplicationContext app: Context,
        @IoDispatcher IoDispatcher: CoroutineDispatcher
    ): TokenStorage = TokenStorage(app, IoDispatcher)

    @Singleton
    @Provides
    fun provideLoginValidator(): LoginValidator = LoginValidator()

    @Singleton
    @Provides
    fun providePasswordValidator(): PasswordValidator = PasswordValidator()

    @Singleton
    @Provides
    fun provideBasePictureModelMapper(): BasePictureModelMapper = BasePictureModelMapper()
}