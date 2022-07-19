package com.oss.gallery.utils

interface BaseOnClickListener {
    fun onLikeClicked(pictureId: Int, isLiked: Boolean)
}