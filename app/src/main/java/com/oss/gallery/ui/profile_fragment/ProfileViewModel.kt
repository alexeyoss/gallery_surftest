package com.oss.gallery.ui.profile_fragment

import com.oss.gallery.ui.states.main_activity_states.MainUiStates
import kotlinx.coroutines.flow.StateFlow

interface ProfileViewModel {

    val logoutFlow: StateFlow<MainUiStates>
    val cleanStorageResourcesFlow: StateFlow<MainUiStates>

    fun logout()
    fun cleanStorageResources()
}