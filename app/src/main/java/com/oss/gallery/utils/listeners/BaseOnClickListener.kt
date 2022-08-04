package com.oss.gallery.utils.listeners

interface BaseOnClickListener {
    fun onLikeClicked(pictureId: Int, isLiked: Boolean)
}