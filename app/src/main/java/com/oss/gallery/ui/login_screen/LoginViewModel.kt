package com.oss.gallery.ui.login_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    fun startValidationProcess(loginInput: String, passwordInput: String) {
        viewModelScope.launch(Dispatchers.IO) {

        }
    }
}