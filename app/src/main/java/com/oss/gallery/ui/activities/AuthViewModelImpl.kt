package com.oss.gallery.ui.activities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oss.gallery.data.repository.MainRepositoryImpl
import com.oss.gallery.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModelImpl
@Inject
constructor(
    private val repository: MainRepositoryImpl,
    @IoDispatcher
    private val IoDispatcher: CoroutineDispatcher
) : ViewModel() {

    fun checkAuthStatus() {
        viewModelScope.launch(IoDispatcher) {

        }
    }
}