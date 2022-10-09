package com.oss.gallery.feature_posts.presentation.main_fragment

import com.oss.gallery.feature_posts.presentation.states.MainUiEvents
import com.oss.gallery.feature_posts.presentation.states.MainUiStates
import kotlinx.coroutines.flow.StateFlow

interface MainFragmentViewModel {
    val picturesUiStateFlow: StateFlow<MainUiStates>
    val picturesUiEventFlow: StateFlow<MainUiEvents>

    fun getPicturesFromNetworkAndMapToBaseModel()
}