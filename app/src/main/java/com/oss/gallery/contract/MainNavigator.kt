package com.oss.gallery.contract

import com.oss.gallery.ui.base_fragments.BaseMainFragments

fun BaseMainFragments.navigator(): MainNavigator = requireActivity() as MainNavigator

interface MainNavigator : BaseNavigator {
    fun hideBottomNavigation()
    fun hideOptionsMenu()
}