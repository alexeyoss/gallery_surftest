package com.oss.gallery.feature_authorization.data.network.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NetworkAuthRequest(
    val phone: String,
    val password: String
) : Parcelable
