package com.oss.gallery.feature_authorization.presentation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.oss.gallery.R

class CustomSnackBar(
    parent: ViewGroup,
    content: CustomSnackBarView
) : BaseTransientBottomBar<CustomSnackBar>(parent, content, content) {

    init {
        view.setBackgroundColor(ContextCompat.getColor(view.context, android.R.color.transparent))
    }

    companion object {
        fun make(viewGroup: ViewGroup, anchorView: View? = null, title: String): CustomSnackBar {
            val customView = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.layout_custom_snackbar, viewGroup, false) as CustomSnackBarView

            customView.title.text = title

            return CustomSnackBar(viewGroup, customView).setDuration(Snackbar.LENGTH_SHORT)
        }
    }
}