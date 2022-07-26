package com.oss.gallery.contract

import androidx.annotation.StringRes

interface HasCustomTitle {

    @StringRes
    fun getStringRes(): Int
}