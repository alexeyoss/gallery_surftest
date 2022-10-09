package com.oss.gallery.feature_posts.utils.listeners

import com.oss.gallery.feature_posts.data.model.BasePictureModel

interface LikeOnClickListener {
    fun onLikeClicked(basePictureModel: BasePictureModel)
}