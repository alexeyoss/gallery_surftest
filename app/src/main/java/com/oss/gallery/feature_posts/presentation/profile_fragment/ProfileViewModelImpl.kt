package com.oss.gallery.feature_posts.presentation.profile_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oss.gallery.di.IoDispatcher
import com.oss.gallery.feature_posts.domain.use_case.MainUseCases
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
class ProfileViewModelImpl
@Inject
constructor(
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher,
    private val mainUseCases: MainUseCases
) : ViewModel(), ProfileViewModel {

    private val _logoutFlow = MutableStateFlow<MainUiStates>(MainUiStates.Empty)
    override val logoutFlow = _logoutFlow.asStateFlow()
    private val _cleanStorageResourcesFlow =
        MutableStateFlow<MainUiStates>(MainUiStates.Empty)
    override val cleanStorageResourcesFlow = _cleanStorageResourcesFlow.asStateFlow()

    override fun logout() {
        viewModelScope.launch(ioDispatcher) {
            mainUseCases.logout()
                .onEach { _logoutFlow.emit(it) }
                .launchIn(viewModelScope)
        }
    }

    override fun cleanStorageResources() {
        viewModelScope.launch {
            mainUseCases.cleanStorageResources()
                .onEach { _cleanStorageResourcesFlow.emit(it) }
                .launchIn(viewModelScope)
        }
    }
}