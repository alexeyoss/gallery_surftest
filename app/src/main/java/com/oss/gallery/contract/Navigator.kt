package com.oss.gallery.contract

import androidx.fragment.app.Fragment

fun Fragment.navigator() : Navigator = requireActivity() as Navigator

interface Navigator {
    fun launchScreen()
    fun goBack()
}