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
import kotlinx.coroutines.job
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

    private val _picturesUiEventFlow = MutableStateFlow<MainUiEvents>(MainUiEvents.GetPictures)
    override val picturesUiEventFlow = _picturesUiEventFlow.asStateFlow()

    init {
        getPicturesFromNetworkAndMapToBaseModel()
    }

    override fun getPicturesFromNetworkAndMapToBaseModel() {
        viewModelScope.launch(ioDispatcher) {
            mainUseCases.GetPicturesFromNetworkAndMapToBaseModelUseCase()
                .onEach {
                    _picturesUiStateFlow.emit(it)
                    _picturesUiEventFlow.emit(MainUiEvents.GetPictures) // TODO  is it correct
                }
                .launchIn(viewModelScope)
        }
    }

    override fun likePostWithTimeStamp(post: BasePictureCachedEntity) {
        viewModelScope.launch(ioDispatcher) {
            mainUseCases.likePostWithTimeStamp(post)
        }
        // TODO GetAll From DB
    }
}