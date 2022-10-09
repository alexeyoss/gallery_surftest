package com.oss.gallery.feature_posts.contract

import androidx.annotation.StringRes

interface ToolbarHandler {

    @StringRes
    fun getStringRes(): Int
}