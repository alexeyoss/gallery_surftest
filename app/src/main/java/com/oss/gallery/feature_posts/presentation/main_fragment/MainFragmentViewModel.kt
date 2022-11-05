package com.oss.gallery.feature_posts.presentation.main_fragment

import com.oss.gallery.feature_posts.data.database.entities.BasePictureCachedEntity
import com.oss.gallery.feature_posts.presentation.states.MainUiEvents
import com.oss.gallery.feature_posts.presentation.states.MainUiStates
import kotlinx.coroutines.flow.StateFlow

interface MainFragmentViewModel {
    val mainPicturesUiStateFlow: StateFlow<MainUiStates>
    val mainPicturesUiEventFlow: StateFlow<MainUiEvents>

    val favoritesPostsUiStateFlow: StateFlow<MainUiStates>
    val favoritesPostsUiEventFlow: StateFlow<MainUiEvents>

    fun getFavoritesPosts()
    fun deleteFavoritePost(post: BasePictureCachedEntity)

    fun onLikeClicked(post: BasePictureCachedEntity)
    fun getAllCachedPosts()
}