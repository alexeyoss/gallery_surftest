package com.oss.gallery.network.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserInfoModel(
    val id: Int,
    val phone: String, // TODO watch the example from the lessons (they do parsing more concise)
    val email: String,
    val city: String,
    val firstName: String,
    val lastName: String,
    val avatar: String,
    val about: String
) : Parcelable