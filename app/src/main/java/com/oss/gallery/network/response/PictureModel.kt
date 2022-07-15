package com.oss.gallery.network.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PictureModel(
    val id: Int,
    val title: String,
    val content: String,
    val photoUrl: String,
    val publicationDate: String
) : Parcelable