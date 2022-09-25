package com.oss.gallery.ui

import android.app.AlertDialog
import android.content.Context
import androidx.annotation.StringRes
import com.oss.gallery.R

object AlertDialogBuilder {

    fun createExitDialog(
        context: Context,
        @StringRes message: Int,
        onSuccess: () -> Unit
    ): AlertDialog {
        return AlertDialog.Builder(context)
            .setCancelable(false)
            .setMessage(message)
            .setPositiveButton(R.string.accept_text) { dialog, _ ->
                onSuccess()
                dialog.dismiss()
            }
            .setNegativeButton(R.string.reject_text) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
    }
}
