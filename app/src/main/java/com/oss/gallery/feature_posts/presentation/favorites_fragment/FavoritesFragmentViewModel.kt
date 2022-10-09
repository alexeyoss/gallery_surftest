package com.oss.gallery.feature_posts.presentation.favorites_fragment

import com.oss.gallery.feature_posts.presentation.states.MainUiEvents
import com.oss.gallery.feature_posts.presentation.states.MainUiStates
import kotlinx.coroutines.flow.StateFlow

interface FavoritesFragmentViewModel {
    val postsUiStateFlow: StateFlow<MainUiStates>
    val postsUiEventFlow: StateFlow<MainUiEvents>

    fun getFavoritesPosts()
    fun deleteFavoritePost()
}