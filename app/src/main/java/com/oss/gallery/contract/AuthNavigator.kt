package com.oss.gallery.contract

import com.oss.gallery.ui.base_fragments.BaseAuthFragments

fun BaseAuthFragments.navigator(): AuthNavigator = requireActivity() as AuthNavigator

interface AuthNavigator : BaseNavigator {
    fun fragmentIsClickable(enable: Boolean)
}

// TODO handle the visibility of the search feature in the options menu