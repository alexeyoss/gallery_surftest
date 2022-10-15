package com.oss.gallery.feature_posts.presentation.states

sealed interface MainUiEvents {
    object GetCachedPostsFromDB : MainUiEvents
    object GetCachedPosts : MainUiEvents
    object Logout : MainUiEvents
    object GetFavoritesPosts : MainUiEvents
}