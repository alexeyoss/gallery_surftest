package com.oss.gallery.di

import com.oss.gallery.data.network.ApiService
import com.oss.gallery.data.repository.BasePictureModelMapper
import com.oss.gallery.data.repository.MainRepositoryImpl
import com.oss.gallery.ui.interactor.InteractorImpl
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
        mainRepository: MainRepositoryImpl
    ): InteractorImpl = InteractorImpl(mainRepository)

    @Singleton
    @Provides
    fun provideRepository(
        apiService: ApiService,
        baseModelMapper: BasePictureModelMapper
    ): MainRepositoryImpl {
        return MainRepositoryImpl(
            apiService,
            baseModelMapper
        )
    }

    @Singleton
    @Provides
    fun provideBasePictureModelMapper(): BasePictureModelMapper = BasePictureModelMapper()
}