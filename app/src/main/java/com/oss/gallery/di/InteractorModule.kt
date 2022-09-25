package com.oss.gallery.di

import com.oss.gallery.data.repository.MainRepositoryImpl
import com.oss.gallery.ui.interactors.AuthInteractorImpl
import com.oss.gallery.ui.interactors.MainInteractorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractorModule {

    @Singleton
    @Provides
    fun provideAuthInteractor(
        mainRepository: MainRepositoryImpl
    ): AuthInteractorImpl = AuthInteractorImpl(mainRepository)

    @Singleton
    @Provides
    fun provideMainInteractor(
        mainRepository: MainRepositoryImpl
    ): MainInteractorImpl = MainInteractorImpl(mainRepository)

//    @Singleton
//    @Provides
//    fun provideBasePictureModelMapper(): BasePictureModelMapper = BasePictureModelMapper()
}