package com.oss.gallery.feature_posts.presentation.main_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oss.gallery.di.IoDispatcher
import com.oss.gallery.feature_posts.data.database.entities.BasePictureCachedEntity
import com.oss.gallery.feature_posts.domain.use_case.MainUseCases
import com.oss.gallery.feature_posts.presentation.states.MainUiEvents
import com.oss.gallery.feature_posts.presentation.states.MainUiStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
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
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher,
    private val mainUseCases: MainUseCases
) : ViewModel(), MainFragmentViewModel {

    private val _picturesUiStateFlow =
        MutableStateFlow<MainUiStates>(MainUiStates.Success(emptyList<BasePictureCachedEntity>()))
    override val picturesUiStateFlow = _picturesUiStateFlow.asStateFlow()

    private val _picturesUiEventFlow =
        MutableStateFlow<MainUiEvents>(MainUiEvents.GetCachedPostsFromDB)
    override val picturesUiEventFlow = _picturesUiEventFlow.asStateFlow()

    init {
        getCachedPicturesFromDbWithNetworkCallUseCase()
    }

    override fun getCachedPicturesFromDbWithNetworkCallUseCase() {
        viewModelScope.launch(ioDispatcher) {
            mainUseCases.getCachedPicturesFromDbWithNetworkCallUseCase()
                .onEach {
                    _picturesUiStateFlow.emit(it)
                    _picturesUiEventFlow.emit(MainUiEvents.GetCachedPostsFromDB)
                }
                .launchIn(viewModelScope)
        }
    }

    override fun onLikeClicked(post: BasePictureCachedEntity) {
        viewModelScope.launch(ioDispatcher) {
            val likeJob = async {
                mainUseCases.onLikeClicked(post)

            }
            likeJob.await().run {
                getAllCachedPosts()
            }
        }
    }

    // TODO incorrect method into PostStorage
    override fun getAllCachedPosts() {
        viewModelScope.launch(ioDispatcher) {
            mainUseCases.getAllCachedPosts()
                .onEach {
                    _picturesUiStateFlow.emit(it)
                    _picturesUiEventFlow.emit(MainUiEvents.GetCachedPosts)
                }.launchIn(viewModelScope)
        }
    }
}