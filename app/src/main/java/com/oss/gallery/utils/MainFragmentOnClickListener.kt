package com.oss.gallery.utils

import com.oss.gallery.network.response.PictureModel

interface MainFragmentOnClickListener : BaseOnClickListener {
    fun onViewClicked(data:PictureModel)
}