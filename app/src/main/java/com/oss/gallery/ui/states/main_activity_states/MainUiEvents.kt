package com.oss.gallery.ui.states.main_activity_states

sealed interface MainUiEvents {
    object GetPictures : MainUiEvents
    object Logout : MainUiEvents
}