package com.oss.gallery.feature_posts.presentation.profile_fragment

import com.oss.gallery.feature_posts.presentation.states.MainUiStates
import kotlinx.coroutines.flow.StateFlow

interface ProfileViewModel {

    val logoutFlow: StateFlow<MainUiStates>
    val cleanStorageResourcesFlow: StateFlow<MainUiStates>

    fun logout()
    fun cleanStorageResources()
}