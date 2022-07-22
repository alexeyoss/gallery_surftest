package com.oss.gallery.data.entites

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BasePictureModel(
    val id: Int,
    val title: String,
    val content: String,
    val photoUrl: String,
    val publicationDate: String,
    val liked: Boolean
) : Parcelable