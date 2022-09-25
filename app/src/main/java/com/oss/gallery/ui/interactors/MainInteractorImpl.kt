package com.oss.gallery.ui.interactors

import com.oss.gallery.data.network.request.ErrorState
import com.oss.gallery.data.network.request.NetworkRequestState
import com.oss.gallery.data.repository.MainRepositoryImpl
import com.oss.gallery.ui.states.main_activity_states.MainUiStates
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainInteractorImpl
@Inject
constructor(
    private val mainRepository: MainRepositoryImpl
) : MainInteractor {
    override suspend fun logout(): Flow<MainUiStates> {
        return flow {
            emit(MainUiStates.Loading)
            val result = when (val requestState = mainRepository.logout()) {
                is NetworkRequestState.Success -> {
                    MainUiStates.Success("")
                }
                else -> MainUiStates.Error(requestState as ErrorState)
            }
            emit(result)
        }
    }

    override suspend fun cleanStorageResources(): Flow<MainUiStates> {
        return flow {
            emit(MainUiStates.Loading)
            val result = mainRepository.cleanStorageResources()

            if (result) {
                emit(MainUiStates.Success(result))
            } else {
                emit(MainUiStates.Error(result))
            }
        }
    }
}