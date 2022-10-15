package com.oss.gallery.feature_posts.utils.listeners

import com.oss.gallery.feature_posts.data.database.entities.BasePictureCachedEntity

interface LikeOnClickListener {
    fun onLikeClicked(post: BasePictureCachedEntity)
}