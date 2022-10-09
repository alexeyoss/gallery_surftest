package com.oss.gallery.feature_authorization.data.network.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NetworkUserInfoResponse(
    val id: String,
    val phone: String, // TODO watch the example from the lessons (they do parsing more concise)
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatar: String,
    val city: String,
    val about: String
) : Parcelable
