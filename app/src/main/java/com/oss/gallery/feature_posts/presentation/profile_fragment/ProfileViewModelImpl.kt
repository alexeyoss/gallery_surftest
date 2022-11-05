package com.oss.gallery.feature_posts.presentation.profile_fragment

import androidx.lifecycle.ViewModel
import com.oss.gallery.feature_posts.domain.use_case.MainUseCases
import com.oss.gallery.feature_posts.presentation.states.MainUiEvents
import com.oss.gallery.feature_posts.presentation.states.MainUiStates
import com.oss.gallery.feature_posts.utils.launchWithIO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModelImpl
@Inject
constructor(
    private val mainUseCases: MainUseCases
) : ViewModel(), ProfileViewModel {

    private val _logoutFlow = MutableStateFlow<MainUiStates>(MainUiStates.Empty)
    override val logoutFlow = _logoutFlow.asStateFlow()

    private val _cleanStorageResourcesFlow =
        MutableStateFlow<MainUiStates>(MainUiStates.Empty)
    override val cleanStorageResourcesFlow = _cleanStorageResourcesFlow.asStateFlow()

    override fun logout() {
        launchWithIO<MainUiStates, MainUiEvents>(
            useCase = { mainUseCases.logout() },
            stateFlow = _logoutFlow
        )
    }

    override fun cleanStorageResources() {

        launchWithIO<MainUiStates, MainUiEvents>(
            useCase = { mainUseCases.cleanStorageResources() },
            stateFlow = _cleanStorageResourcesFlow
        )
    }
}