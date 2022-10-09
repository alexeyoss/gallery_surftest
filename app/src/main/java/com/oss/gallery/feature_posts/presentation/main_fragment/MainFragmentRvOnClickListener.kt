package com.oss.gallery.feature_posts.presentation.main_fragment

import com.oss.gallery.feature_posts.data.model.BasePictureModel
import com.oss.gallery.feature_posts.utils.listeners.BaseOnClickListener
import com.oss.gallery.feature_posts.utils.listeners.LikeOnClickListener

interface MainFragmentRvOnClickListener : BaseOnClickListener, LikeOnClickListener {
    fun onPostClicked(data: BasePictureModel)
}