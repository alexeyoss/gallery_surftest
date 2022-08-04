package com.oss.gallery.data.network.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NetworkAuthResponse(
    val token: String,
    val user_info: NetworkUserInfoResponse
) : Parcelable
