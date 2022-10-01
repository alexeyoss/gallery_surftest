package com.oss.gallery.ui.main_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oss.gallery.data.network.response.NetworkPictureResponse
import com.oss.gallery.di.IoDispatcher
import com.oss.gallery.ui.interactors.MainInteractor
import com.oss.gallery.ui.states.main_activity_states.MainUiEvents
import com.oss.gallery.ui.states.main_activity_states.MainUiStates
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
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher,
    private val interactor: MainInteractor
) : ViewModel(), MainFragmentViewModel {

    private val _picturesUiStateFlow =
        MutableStateFlow<MainUiStates>(MainUiStates.Success(emptyList<NetworkPictureResponse>()))
    override val picturesUiStateFlow = _picturesUiStateFlow.asStateFlow()

    private val _picturesUiEventFlow = MutableStateFlow<MainUiEvents>(MainUiEvents.GetPictures)
    override val picturesUiEventFlow = _picturesUiEventFlow.asStateFlow()

    init {
        getPictures()
    }

    override fun getPictures() {
        viewModelScope.launch(ioDispatcher) {
            interactor.getPictureFromTheNetwork()
                .onEach { _picturesUiStateFlow.emit(it) }
                .launchIn(viewModelScope)
        }
    }
}