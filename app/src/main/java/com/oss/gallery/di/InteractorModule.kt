package com.oss.gallery.di

import com.oss.gallery.ui.interactors.AuthInteractor
import com.oss.gallery.ui.interactors.AuthInteractorImpl
import com.oss.gallery.ui.interactors.MainInteractor
import com.oss.gallery.ui.interactors.MainInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class InteractorModule {

    @Binds
    abstract fun bindAuthInteractor(impl: AuthInteractorImpl): AuthInteractor

    @Binds
    abstract fun bindMainInteractor(impl: MainInteractorImpl): MainInteractor
}