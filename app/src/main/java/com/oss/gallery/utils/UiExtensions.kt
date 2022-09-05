package com.oss.gallery.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.oss.gallery.R

fun AppCompatActivity.replaceFragment(
    fragment: Fragment,
    addStack: Boolean = false
) {
    if (addStack) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragmentContainer, fragment, fragment.toString())
            .commit()
    } else {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment, fragment.toString())
            .commit()
    }
}
