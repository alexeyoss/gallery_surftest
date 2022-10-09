package com.oss.gallery.feature_authorization.data.network.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NetworkErrorResponse(
    val code: String,
    val reason: String
) : Parcelable
