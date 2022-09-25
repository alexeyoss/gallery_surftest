package com.oss.gallery.ui.interactors

import com.oss.gallery.ui.states.main_activity_states.MainUiStates
import kotlinx.coroutines.flow.Flow

interface MainInteractor : BaseInteractor {
    suspend fun logout(): Flow<MainUiStates>
    suspend fun cleanStorageResources(): Flow<MainUiStates>
}