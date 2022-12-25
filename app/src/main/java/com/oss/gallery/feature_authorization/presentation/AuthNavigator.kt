package com.oss.gallery.feature_authorization.presentation

import com.oss.gallery.feature_authorization.presentation.login_fragment.LoginFragment

fun LoginFragment.navigator(): AuthNavigator = requireActivity() as AuthNavigator

interface AuthNavigator {
    fun changeActivity()
    fun goBack()
}
