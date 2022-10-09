package com.oss.gallery.feature_posts.contract

import com.oss.gallery.feature_posts.presentation.BaseMainFragments

fun BaseMainFragments.navigator(): MainNavigator = requireActivity() as MainNavigator

interface MainNavigator : BaseNavigator {
    fun hideBottomNavigation()
    fun hideOptionsMenu()
}