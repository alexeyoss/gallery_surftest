package com.oss.gallery.feature_posts.presentation.main_fragment

import androidx.lifecycle.ViewModel
import com.oss.gallery.feature_posts.data.database.entities.BasePictureCachedEntity
import com.oss.gallery.feature_posts.domain.use_case.MainUseCases
import com.oss.gallery.feature_posts.presentation.states.MainUiEvents
import com.oss.gallery.feature_posts.presentation.states.MainUiEvents.*
import com.oss.gallery.feature_posts.presentation.states.MainUiStates
import com.oss.gallery.feature_posts.utils.launchWithIO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModelImpl
@Inject
constructor(
    private val mainUseCases: MainUseCases
) : ViewModel(), MainFragmentViewModel {

    private val _mainPicturesUiStateFlow =
        MutableStateFlow<MainUiStates>(MainUiStates.Empty)
    override val mainPicturesUiStateFlow = _mainPicturesUiStateFlow.asStateFlow()

    private val _mainPicturesUiEventFlow =
        MutableStateFlow<MainUiEvents>(Initial)
    override val mainPicturesUiEventFlow = _mainPicturesUiEventFlow.asStateFlow()

    private val _favoritesPostsUiStateFlow = MutableStateFlow<MainUiStates>(MainUiStates.Empty)
    override val favoritesPostsUiStateFlow = _favoritesPostsUiStateFlow.asStateFlow()

    private val _favoritesPostsUiEventFlow =
        MutableStateFlow<MainUiEvents>(Initial)
    override val favoritesPostsUiEventFlow = _favoritesPostsUiEventFlow

    init {
        launchWithIO(
            useCase = { mainUseCases.getCachedPicturesFromDbWithNetworkCallUseCase() },
            stateFlow = _mainPicturesUiStateFlow,
            eventFlow = _mainPicturesUiEventFlow,
            event = GetCachedPosts
        )
    }

    override fun onLikeClicked(post: BasePictureCachedEntity) {
        launchWithIO(
            useCase = { mainUseCases.onLikeClicked(post) },
            stateFlow = _mainPicturesUiStateFlow,
            eventFlow = _mainPicturesUiEventFlow,
            event = LikePost
        )
    }

    override fun getAllCachedPosts() {
        launchWithIO(
            useCase = { mainUseCases.getAllCachedPosts() },
            stateFlow = _mainPicturesUiStateFlow,
            eventFlow = _mainPicturesUiEventFlow,
            event = GetCachedPostsFromDB
        )
    }

    override fun getFavoritesPosts() {
        launchWithIO(
            useCase = { mainUseCases.getFavoritesPosts() },
            stateFlow = _favoritesPostsUiStateFlow,
            eventFlow = _favoritesPostsUiEventFlow,
            event = GetFavoritesPosts
        )
    }

    override fun deleteFavoritePost(post: BasePictureCachedEntity) {
        launchWithIO(
            useCase = { mainUseCases.deleteLikedPost(post) },
            stateFlow = _favoritesPostsUiStateFlow,
            eventFlow = _favoritesPostsUiEventFlow,
            event = DeleteFavoritesPosts
        )
    }
}

