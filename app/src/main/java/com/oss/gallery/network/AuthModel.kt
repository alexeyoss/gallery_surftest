package com.oss.gallery.network

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AuthModel(
    val user_info: UserInfoModel,
    val token: String
) : Parcelable
