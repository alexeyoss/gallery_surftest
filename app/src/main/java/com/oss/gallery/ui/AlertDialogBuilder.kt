package com.oss.gallery.ui

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

class AlertDialogBuilder {
    companion object {
        fun createDialog(
            context: Context,
            message: String,
            buttonListener: DialogInterface.OnClickListener
        ): AlertDialog {
            return AlertDialog.Builder(context)
                .setCancelable(false)
                .setMessage(message)
                .setPositiveButton("ДА, ТОЧНО", buttonListener)
                .setNegativeButton("НЕТ", buttonListener)
                .create()
        }
    }
}