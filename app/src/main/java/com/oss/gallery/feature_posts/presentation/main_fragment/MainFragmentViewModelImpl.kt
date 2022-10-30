package com.oss.gallery.feature_posts.presentation.main_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oss.gallery.di.CoroutinesModule
import com.oss.gallery.feature_posts.data.database.entities.BasePictureCachedEntity
import com.oss.gallery.feature_posts.domain.use_case.MainUseCases
import com.oss.gallery.feature_posts.presentation.states.MainUiEvents
import com.oss.gallery.feature_posts.presentation.states.MainUiStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModelImpl
@Inject
constructor(
    @CoroutinesModule.IoDispatcher
    private val ioDispatcher: CoroutineDispatcher,
    private val mainUseCases: MainUseCases
) : ViewModel(), MainFragmentViewModel {

    private val _mainPicturesUiStateFlow =
        MutableStateFlow<MainUiStates>(MainUiStates.Empty)
    override val mainPicturesUiStateFlow = _mainPicturesUiStateFlow.asStateFlow()

    private val _mainPicturesUiEventFlow =
        MutableStateFlow<MainUiEvents>(MainUiEvents.Initial)
    override val mainPicturesUiEventFlow = _mainPicturesUiEventFlow.asStateFlow()

    private val _favoritesPostsUiStateFlow = MutableStateFlow<MainUiStates>(MainUiStates.Empty)
    override val favoritesPostsUiStateFlow = _favoritesPostsUiStateFlow.asStateFlow()

    private val _favoritesPostsUiEventFlow =
        MutableStateFlow<MainUiEvents>(MainUiEvents.Initial)
    override val favoritesPostsUiEventFlow = _favoritesPostsUiEventFlow

    init {
        getCachedPicturesFromDbWithNetworkCallUseCase()
    }

    override fun getCachedPicturesFromDbWithNetworkCallUseCase() {
        viewModelScope.launch(ioDispatcher) {
            mainUseCases.getCachedPicturesFromDbWithNetworkCallUseCase()
                .onEach {
                    _mainPicturesUiStateFlow.emit(it)
                    _mainPicturesUiEventFlow.emit(MainUiEvents.GetCachedPosts)
                }
                .launchIn(viewModelScope)
        }
    }

    override fun onLikeClicked(post: BasePictureCachedEntity) {
        viewModelScope.launch(ioDispatcher) {
            mainUseCases.onLikeClicked(post)
                .onEach {
                    _mainPicturesUiStateFlow.emit(it)
                    _mainPicturesUiEventFlow.emit(MainUiEvents.LikePost)
                }
                .launchIn(viewModelScope)

        }
    }

    override fun getAllCachedPosts() {
        viewModelScope.launch(ioDispatcher) {
            mainUseCases.getAllCachedPosts()
                .onEach {
                    _mainPicturesUiStateFlow.emit(it)
                    _mainPicturesUiEventFlow.emit(MainUiEvents.GetCachedPostsFromDB)
                }.launchIn(viewModelScope)
        }
    }

    override fun getFavoritesPosts() {
        viewModelScope.launch(ioDispatcher) {
            mainUseCases.getFavoritesPosts()
                .onEach {
                    _favoritesPostsUiStateFlow.emit(it)
                    _favoritesPostsUiEventFlow.emit(MainUiEvents.GetFavoritesPosts)
                }
                .launchIn(viewModelScope)
        }
    }

    override fun deleteFavoritePost(post: BasePictureCachedEntity) {
        viewModelScope.launch(ioDispatcher) {
            mainUseCases.deleteLikedPost(post)
                .onEach {
                    _favoritesPostsUiStateFlow.emit(it)
                }
                .launchIn(viewModelScope)
        }
    }
}