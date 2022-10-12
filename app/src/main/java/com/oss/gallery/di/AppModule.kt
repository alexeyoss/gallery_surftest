package com.oss.gallery.di

import android.content.Context
import com.oss.gallery.App
import com.oss.gallery.feature_authorization.presentation.util.LoginValidator
import com.oss.gallery.feature_authorization.presentation.util.PasswordValidator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): App = app as App

    @Singleton
    @Provides
    fun provideLoginValidator(): LoginValidator = LoginValidator()

    @Singleton
    @Provides
    fun providePasswordValidator(): PasswordValidator = PasswordValidator()
}