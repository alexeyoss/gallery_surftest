package com.oss.gallery.ui.main_fragment

import androidx.lifecycle.ViewModel
import com.oss.gallery.di.IoDispatcher
import com.oss.gallery.ui.states.main_activity_states.MainUiEvents
import com.oss.gallery.ui.states.main_activity_states.MainUiStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModelImpl
@Inject
constructor(
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel(), MainFragmentViewModel {
    override val mainUiStateFlow = MutableStateFlow<MainUiStates>(MainUiStates.Empty)
    override val mainUiEventFlow = MutableStateFlow<MainUiEvents>(MainUiEvents.GetPictures)

    override fun getPictures(token: String) {
        TODO("Not yet implemented")
    }
}