package com.oss.gallery.feature_posts.presentation.utils

import androidx.fragment.app.Fragment
import com.oss.gallery.feature_posts.presentation.BaseMainFragments

fun BaseMainFragments.navigator(): MainNavigator = requireActivity() as MainNavigator

interface MainNavigator {
    fun changeFragment(fragment: Fragment?)
    fun goBack()
    fun hideBottomNavigation()
    fun hideOptionsMenu()
}