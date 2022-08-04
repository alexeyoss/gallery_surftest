package com.oss.gallery.ui.interactor

import com.oss.gallery.data.repository.MainRepositoryImpl
import com.oss.gallery.ui.states.AuthUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class InteractorImpl constructor(
    private val mainRepository: MainRepositoryImpl
) : Interactor {

    suspend fun login(): Flow<AuthUiState> = flow {
        emit(AuthUiState.Loading)

    }
}