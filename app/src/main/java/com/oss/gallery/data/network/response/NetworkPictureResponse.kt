package com.oss.gallery.data.network.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NetworkPictureResponse(
    val id: Int,
    val title: String,
    val content: String,
    val photoUrl: String,
    val publicationDate: String
) : Parcelable