package com.oss.gallery.di

import com.oss.gallery.data.Interactor
import com.oss.gallery.data.entites.BasePictureModelMapper
import com.oss.gallery.network.ApiService
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
    fun provideInteractor(
        apiService: ApiService,
        baseModelMapper: BasePictureModelMapper
    ): Interactor {
        return Interactor(
            apiService,
            baseModelMapper
        )
    }

    @Singleton
    @Provides
    fun provideBasePictureModelMapper(): BasePictureModelMapper = BasePictureModelMapper()
}