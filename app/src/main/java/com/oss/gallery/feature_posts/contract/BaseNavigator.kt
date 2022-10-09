package com.oss.gallery.feature_posts.contract

import androidx.fragment.app.Fragment

interface BaseNavigator {
    fun changeActivity(fragment: Fragment?)
    fun goBack()
}