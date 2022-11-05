package com.oss.gallery.feature_posts.presentation.states

sealed interface MainUiEvents {
    object Initial : MainUiEvents
    object GetCachedPostsFromDB : MainUiEvents
    object GetCachedPosts : MainUiEvents
    object LikePost : MainUiEvents
    object Logout : MainUiEvents
    object GetFavoritesPosts : MainUiEvents
    object DeleteFavoritesPosts : MainUiEvents
}