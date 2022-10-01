package com.oss.gallery.ui.profile_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oss.gallery.di.IoDispatcher
import com.oss.gallery.ui.interactors.MainInteractor
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
class ProfileViewModelImpl
@Inject
constructor(
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher,
    private val interactor: MainInteractor
) : ViewModel(), ProfileViewModel {

    private val _logoutFlow = MutableStateFlow<MainUiStates>(MainUiStates.Empty)
    override val logoutFlow = _logoutFlow.asStateFlow()
    private val _cleanStorageResourcesFlow =
        MutableStateFlow<MainUiStates>(MainUiStates.Empty)
    override val cleanStorageResourcesFlow = _cleanStorageResourcesFlow.asStateFlow()

    override fun logout() {
        viewModelScope.launch(ioDispatcher) {
            interactor.logout()
                .onEach { _logoutFlow.emit(it) }
                .launchIn(viewModelScope)
        }
    }

    override fun cleanStorageResources() {
        viewModelScope.launch {
            interactor.cleanStorageResources()
                .onEach { _cleanStorageResourcesFlow.emit(it) }
                .launchIn(viewModelScope)
        }
    }
}