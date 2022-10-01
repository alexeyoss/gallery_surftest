package com.oss.gallery.utils

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
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

@SuppressLint("ResourceAsColor")
fun TextInputLayout.setErrorStateForTextInputLayout(
    message: String
) {
    apply {
        error = message
        boxStrokeColor = R.color.input_stroke_ErrorColor
        errorIconDrawable = null
    }
}
