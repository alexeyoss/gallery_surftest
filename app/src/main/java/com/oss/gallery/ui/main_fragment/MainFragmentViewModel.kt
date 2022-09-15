package com.oss.gallery.ui.main_fragment

import com.oss.gallery.ui.states.main_activity_states.MainUiEvents
import com.oss.gallery.ui.states.main_activity_states.MainUiStates
import kotlinx.coroutines.flow.StateFlow

interface MainFragmentViewModel {
    val mainUiStateFlow: StateFlow<MainUiStates>
    val mainUiEventFlow: StateFlow<MainUiEvents>

    fun getPictures(token: String)
}