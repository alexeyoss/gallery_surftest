package com.oss.gallery.contract

import androidx.annotation.StringRes

interface ToolbarHandler {

    @StringRes
    fun getStringRes(): Int
}