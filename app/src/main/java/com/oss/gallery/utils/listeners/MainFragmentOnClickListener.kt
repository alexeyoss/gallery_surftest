package com.oss.gallery.utils.listeners

import com.oss.gallery.data.model.BasePictureModel

interface MainFragmentOnClickListener : BaseOnClickListener, LikeOnClickListener {
    fun onViewClicked(data: BasePictureModel)
}