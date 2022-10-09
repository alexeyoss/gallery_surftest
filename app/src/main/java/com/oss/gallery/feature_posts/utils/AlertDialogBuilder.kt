package com.oss.gallery.feature_posts.utils

import android.app.AlertDialog
import android.content.Context
import androidx.annotation.StringRes
import com.oss.gallery.R

object AlertDialogBuilder {

    fun createDialog(
        context: Context,
        @StringRes message: Int,
        onPositive: () -> Unit,
        onNegative: () -> Unit
    ): AlertDialog {
        return AlertDialog.Builder(context)
            .setCancelable(false)
            .setMessage(message)
            .setPositiveButton(R.string.accept_text) { dialog, _ ->
                onPositive()
                dialog.dismiss()
            }
            .setNegativeButton(R.string.reject_text) { dialog, _ ->
                onNegative()
                dialog.dismiss()
            }
            .create()
    }
}
