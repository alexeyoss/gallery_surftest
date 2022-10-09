package com.oss.gallery.feature_posts.presentation.states

sealed interface MainUiEvents {
    object GetPictures : MainUiEvents
    object Logout : MainUiEvents
    object GetFavoritesPosts : MainUiEvents
}