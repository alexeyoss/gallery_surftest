package com.oss.gallery.feature_posts.contract

import com.oss.gallery.feature_authorization.presentation.BaseAuthFragments

fun BaseAuthFragments.navigator(): AuthNavigator = requireActivity() as AuthNavigator

interface AuthNavigator : BaseNavigator {
    fun fragmentIsClickable(enable: Boolean)
}

// TODO handle the visibility of the search feature in the options menu