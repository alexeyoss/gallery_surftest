package com.oss.gallery.feature_posts.presentation.favorites_fragment

import com.oss.gallery.feature_posts.presentation.states.MainUiEvents
import com.oss.gallery.feature_posts.presentation.states.MainUiStates
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FavoritesFragmentViewModelImpl : FavoritesFragmentViewModel {

    private val _postsUiStateFlow = MutableStateFlow<MainUiStates>(MainUiStates.Empty)
    override val postsUiStateFlow = _postsUiStateFlow.asStateFlow()
    private val _postsUiEventFlow = MutableStateFlow<MainUiEvents>(MainUiEvents.GetFavoritesPosts)
    override val postsUiEventFlow = _postsUiEventFlow.asStateFlow()

    override fun getFavoritesPosts() {
        TODO("Not yet implemented")
    }

    override fun deleteFavoritePost() {
        TODO("Not yet implemented")
    }
}