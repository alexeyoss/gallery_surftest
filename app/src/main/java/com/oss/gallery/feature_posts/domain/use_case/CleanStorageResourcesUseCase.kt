package com.oss.gallery.feature_posts.domain.use_case

import com.oss.gallery.feature_posts.domain.repository.MainRepository
import com.oss.gallery.feature_posts.presentation.states.MainUiStates
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CleanStorageResourcesUseCase
@Inject
constructor(
    private val repository: MainRepository
) {
    suspend operator fun invoke(): Flow<MainUiStates> {
        return flow {
            emit(MainUiStates.Loading)
            val result = repository.cleanStorageResources()

            if (result) {
                emit(MainUiStates.Success(result))
            } else {
                emit(MainUiStates.Error(result))
            }
        }
    }
}