package com.oss.gallery.contract

import androidx.fragment.app.Fragment

interface BaseNavigator {
    fun changeActivity(fragment: Fragment?)
    fun goBack()
}